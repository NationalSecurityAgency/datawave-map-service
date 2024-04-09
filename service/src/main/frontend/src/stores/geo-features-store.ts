import { defineStore } from 'pinia';
import { GeoFeatures } from 'src/components/models';
import { api } from 'boot/axios'
import { ManualGeometryForm } from 'src/components/models';

export const geoFeaturesStore = defineStore('geoQueryFeatures', {
  state: () => ({
    geoQueryFeatures: {} as GeoFeaturesMap,
  }),
  getters: {
      getGeoFeatures: (state) => state.geoQueryFeatures,
      getGeoFeaturesById: (state) => {
          return (queryId: string) => state.geoQueryFeatures[queryId];
      }
  },
  actions: {
    async loadGeoFeaturesForQuery(query: string, fieldTypes?: Map<string, string[]>) {
        console.log('backend base path: ' + process.env.BACKEND_BASE_PATH);
        console.log('query: ' + query);

        const params: {plan: string, fieldTypes?: string, expand: boolean} = {
          plan: query,
          expand: true
        };

        let fieldTypesString = '';
        if (fieldTypes != null) {
          fieldTypes.forEach((value, key) => {
            if (fieldTypesString.length > 0){
              fieldTypesString += ',';
            }
            fieldTypesString += key + ':' + value;
          });
          params.fieldTypes = fieldTypesString;
        }

        api.post('/map/v1/getGeoFeatures', null, {
          params: params
        })
        .then((response) => {
            this.geoQueryFeatures[query] = response.data;
        })
        .catch((reason) => {
            console.log('Something went wrong? ' + reason);
        })
    },
    async loadGeoFeaturesFromGeometry(geometryFormData: ManualGeometryForm) {
      api.post('/map/v1/geoFeaturesFromGeometry', null, {
        params: {
          geometry: geometryFormData.geometry,
          geometryType: geometryFormData.geometryType,
          createRanges: geometryFormData.createRanges,
          rangeType: geometryFormData.rangeType,
          maxEnvelopes: geometryFormData.rangeSettings[geometryFormData.rangeType].maxEnvelopes,
          maxExpansion: geometryFormData.rangeSettings[geometryFormData.rangeType].maxExpansion,
          optimizeRanges: geometryFormData.rangeSettings[geometryFormData.rangeType].optimizeRanges,
          rangeSplitThreshold: geometryFormData.rangeSettings[geometryFormData.rangeType].rangeSplitThreshold,
          maxRangeOverlap: geometryFormData.rangeSettings[geometryFormData.rangeType].maxRangeOverlap
        }
      })
      .then((response) => {
          this.geoQueryFeatures[new Date().toLocaleString()] = response.data;
      })
      .catch((reason) => {
          console.log('Something went wrong? ' + reason);
      })
    },
  }
});

interface GeoFeaturesMap {
    [queryId: string]: GeoFeatures;
}

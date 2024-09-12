import { Basemap } from './basemaps-store';

// get Leaflet
const L = window.L;

export class SimpleMapStore {
  map: L.Map | null = null;
  layerControl: L.Control.Layers | null = null;
  basemap: Basemap | null = null;

  createMap(
    mapId: string,
    enableAttribution: boolean,
    enableZoomControl: boolean,
    bounds: L.LatLngBounds,
    minZoom: number,
    crs: string
  ) {
    let CRS;
    switch (crs) {
      case 'Earth':
        CRS = L.CRS.Earth;
        break;
      case 'EPSG3395':
        CRS = L.CRS.EPSG3395;
        break;
      case 'EPSG4326':
        CRS = L.CRS.EPSG4326;
        break;
      case 'Simple':
        CRS = L.CRS.Simple;
        break;
      case 'EPSG3857':
      default:
        CRS = L.CRS.EPSG3857;
        break;
    }

    this.map = L.map(mapId, {
      crs: CRS,
      attributionControl: enableAttribution,
      zoomControl: enableZoomControl,
      maxBounds: bounds,
      minZoom: minZoom,
    });
  }

  setView(center: L.LatLngExpression, zoom?: number) {
    this.map?.setView(center, zoom);
  }

  invalidateSize(options?: boolean) {
    this.map?.invalidateSize(options);
  }

  enableLayerControl(enable: boolean) {
    if (enable && this.layerControl == null) {
      this.layerControl = L.control.layers().addTo(this.map as L.Map);
    } else {
      this.layerControl?.remove();
      this.layerControl = null;
    }
  }

  setBasemap(basemap: Basemap) {
    const layerControl = this.layerControl;

    if (this.basemap != null) {
      this.basemap.tileLayer.remove();

      // remove the tile layer from the control
      if (layerControl != null) {
        layerControl.removeLayer(basemap.tileLayer);
      }
    }

    this.basemap = basemap;

    const map = this.map as L.Map;
    basemap.tileLayer.addTo(map);

    // add the layer to the control
    if (layerControl != null) {
      layerControl.addBaseLayer(basemap.tileLayer, basemap.title);
    }
  }

  addLayerGroup(layerGroup: L.LayerGroup) {
    layerGroup.addTo(this.map as L.Map);
  }

  addLayer(layer: L.Layer) {
    layer.addTo(this.map as L.Map);
  }

  hasLayer(layer: L.Layer): boolean {
    let hasLayer = this.map?.hasLayer(layer);
    if (hasLayer == undefined) {
      hasLayer = false;
    }
    return hasLayer;
  }

  addControl(control: L.Control) {
    control.addTo(this.map as L.Map);
  }

  removeLayer(layer: L.Layer) {
    this.map?.removeLayer(layer);
  }

  fitBounds(bounds: L.LatLngBounds) {
    this.map?.fitBounds(bounds);
  }
}

export const simpleMapStore = new SimpleMapStore();

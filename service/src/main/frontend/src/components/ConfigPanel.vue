<template>
  <AddContentPanel
    v-if="appStateStore().getConfigPanelSelection === 'Add'"
    :supportedGeometries="supportedGeometries"
  />
  <ContentPanel
    v-else-if="appStateStore().getConfigPanelSelection === 'Content'"
  />
  <BasemapPanel
    v-else-if="appStateStore().getConfigPanelSelection === 'Basemaps'"
    :previewTileCoords="previewTileCoords"
  />
  <GeoToolboxPanel
    v-else-if="appStateStore().getConfigPanelSelection === 'Geo Toolbox'"
    :supportedGeometries="supportedGeometries"
  />
</template>

<script setup lang="ts">
import { appStateStore } from 'stores/state-store';
import AddContentPanel from 'components/AddContentPanel.vue';
import ContentPanel from 'components/ContentPanel.vue';
import BasemapPanel from 'components/BasemapPanel.vue';
import GeoToolboxPanel from 'components/GeoToolboxPanel.vue';
import { Coordinate } from 'components/models';

export interface ConfigPanelProps {
  supportedGeometries: string[];
  previewTileCoords: Coordinate;
}
defineProps<ConfigPanelProps>();
</script>

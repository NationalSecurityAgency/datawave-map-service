package datawave.microservice.map.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@ConfigurationProperties(prefix = "datawave.map")
public class MapServiceProperties {
    private String metricsUri = "https://metrics:8443/querymetric/v1/id/";
    private String metadataTableName;
    private List<Basemap> basemaps = new ArrayList<>();
    private Banner header = new Banner();
    private Banner footer = new Banner();
    private List<String> supportedGeometries = Arrays.asList("WKT", "GeoJSON");
    private CRS crs = CRS.EPSG3857;
    private Coordinate previewTileCoords = new Coordinate(18, 24, 6);
    
    public String getMetricsUri() {
        return metricsUri;
    }
    
    public void setMetricsUri(String metricsUri) {
        this.metricsUri = metricsUri;
    }
    
    public String getMetadataTableName() {
        return metadataTableName;
    }
    
    public void setMetadataTableName(String metadataTableName) {
        this.metadataTableName = metadataTableName;
    }
    
    public List<Basemap> getBasemaps() {
        return basemaps;
    }
    
    public void setBasemaps(List<Basemap> basemaps) {
        this.basemaps = basemaps;
    }
    
    public Banner getHeader() {
        return header;
    }
    
    public void setHeader(Banner header) {
        this.header = header;
    }
    
    public Banner getFooter() {
        return footer;
    }
    
    public void setFooter(Banner footer) {
        this.footer = footer;
    }
    
    public List<String> getSupportedGeometries() {
        return supportedGeometries;
    }
    
    public void setSupportedGeometries(List<String> supportedGeometries) {
        this.supportedGeometries = supportedGeometries;
    }
    
    public CRS getCrs() {
        return crs;
    }
    
    public void setCrs(CRS crs) {
        this.crs = crs;
    }
    
    public Coordinate getPreviewTileCoords() {
        return previewTileCoords;
    }
    
    public void setPreviewTileCoords(Coordinate previewTileCoords) {
        this.previewTileCoords = previewTileCoords;
    }
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Basemap {
        private String title;
        private String urlTemplate;
        @JsonProperty()
        private Integer maxZoom;
        private Integer maxNativeZoom;
        private String attribution;
        @JsonProperty("default")
        private Boolean defaultEnabled;
        
        public String getTitle() {
            return title;
        }
        
        public void setTitle(String title) {
            this.title = title;
        }
        
        public String getUrlTemplate() {
            return urlTemplate;
        }
        
        public void setUrlTemplate(String urlTemplate) {
            this.urlTemplate = urlTemplate;
        }
        
        public Integer getMaxZoom() {
            return maxZoom;
        }
        
        public void setMaxZoom(Integer maxZoom) {
            this.maxZoom = maxZoom;
        }
        
        public Integer getMaxNativeZoom() {
            return maxNativeZoom;
        }
        
        public void setMaxNativeZoom(Integer maxNativeZoom) {
            this.maxNativeZoom = maxNativeZoom;
        }
        
        public String getAttribution() {
            return attribution;
        }
        
        public void setAttribution(String attribution) {
            this.attribution = attribution;
        }
        
        public Boolean isDefaultEnabled() {
            return defaultEnabled;
        }
        
        public void setDefaultEnabled(Boolean defaultEnabled) {
            this.defaultEnabled = defaultEnabled;
        }
    }
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Banner {
        private boolean enabled = false;
        private String message;
        private String style;
        
        public boolean isEnabled() {
            return enabled;
        }
        
        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
        
        public String getMessage() {
            return message;
        }
        
        public void setMessage(String message) {
            this.message = message;
        }
        
        public String getStyle() {
            return style;
        }
        
        public void setStyle(String style) {
            this.style = style;
        }
    }
    
    public enum CRS {
        Earth, EPSG3395, EPSG3857, EPSG4326, Simple
    }
    
    public static class Coordinate {
        private int x;
        private int y;
        private int z;
        
        public Coordinate() {
            
        }
        
        public Coordinate(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
        
        public int getX() {
            return x;
        }
        
        public void setX(int x) {
            this.x = x;
        }
        
        public int getY() {
            return y;
        }
        
        public void setY(int y) {
            this.y = y;
        }
        
        public int getZ() {
            return z;
        }
        
        public void setZ(int z) {
            this.z = z;
        }
    }
}

package datawave.microservice.map.geojson;

import java.io.IOException;

import org.geotools.feature.FeatureCollection;
import org.geotools.geojson.feature.FeatureJSON;
import org.geotools.geojson.geom.GeometryJSON;
import org.locationtech.jts.geom.Geometry;
import org.opengis.feature.Feature;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 * This is copied from org.geotools.geojson.GeoJSON, but increases the number of decimals to print.
 */
public class GeoJSON {
    static final int DECIMALS = 7;
    static GeometryJSON gjson = new GeometryJSON(DECIMALS);
    static FeatureJSON fjson = new FeatureJSON(new GeometryJSON(DECIMALS));
    
    public static Object read(Object input) throws IOException {
        throw new UnsupportedOperationException();
    }
    
    public static void write(Object obj, Object output) throws IOException {
        if (obj instanceof Geometry) {
            gjson.write((Geometry) obj, output);
        } else if (obj instanceof Feature || obj instanceof FeatureCollection || obj instanceof CoordinateReferenceSystem) {
            
            if (obj instanceof SimpleFeature) {
                fjson.writeFeature((SimpleFeature) obj, output);
            } else if (obj instanceof FeatureCollection) {
                fjson.writeFeatureCollection((FeatureCollection) obj, output);
            } else if (obj instanceof CoordinateReferenceSystem) {
                fjson.writeCRS((CoordinateReferenceSystem) obj, output);
            } else {
                throw new IllegalArgumentException("Unable able to encode object of type " + obj.getClass());
            }
        }
    }
}

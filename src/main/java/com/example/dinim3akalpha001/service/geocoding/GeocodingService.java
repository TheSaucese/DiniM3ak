/*
 * Copyright 2014 Lynden, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.dinim3akalpha001.service.geocoding;

import com.example.dinim3akalpha001.javascript.JavascriptObject;
import com.example.dinim3akalpha001.javascript.object.GMapObjectType;
import com.example.dinim3akalpha001.javascript.object.LatLong;
import netscape.javascript.JSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Rob Terpilowski
 */
public class GeocodingService extends JavascriptObject {
    private static final Logger LOG = LoggerFactory.getLogger(GeocodingService.class);
    
    public GeocodingServiceCallback callback;
    
    public GeocodingService(){
        super(GMapObjectType.GEOCODER);
    }
    
    public void reverseGeocode(double lat, double lon,GeocodingServiceCallback callback){
        this.getGeocoding(new GeocoderRequest(null, new LatLong(lat, lon),null, null, null, null), callback);
    }
    
    public void geocode(String address, GeocodingServiceCallback callback){
        this.getGeocoding(new GeocoderRequest(address), callback);
    }
    
    public void getGeocoding(GeocoderRequest req, GeocodingServiceCallback callback){
        this.callback = callback;
        
        JSObject doc = (JSObject) getJSObject().eval("document");
        doc.setMember(getVariableName(), this);
        
        StringBuilder r = new StringBuilder(getVariableName())
              .append(".")
              .append("geocode(")
              .append(req.getVariableName())
              .append(", ")
              .append("function(results, status) {alert('rec:'+status);\ndocument.")
              .append(getVariableName())
              .append(".processResponse(results, status);});");
        
        LOG.trace("Geocoding direct call: " + r.toString());
        
        getJSObject().eval(r.toString());
    }
    
    
    public void processResponse(Object results, Object status) {
        GeocoderStatus pStatus = GeocoderStatus.UNKNOWN_ERROR;
        if (status instanceof String && results instanceof JSObject) {
            pStatus = GeocoderStatus.valueOf((String) status);
            if (GeocoderStatus.OK.equals(pStatus)) {
                JSObject jsres = (JSObject) results;
                Object len = jsres.getMember("length");
                if (len instanceof Number) {
                    int n = ((Number)len).intValue();
//                    LOG.trace("n: " + n);
                    GeocodingResult[] ers = new GeocodingResult[n];
                    for (int i = 0; i < n; i++) {
                        Object obj = jsres.getSlot(i);
                        if (obj instanceof JSObject) {
                            ers[i] = new GeocodingResult((JSObject) obj);
                        }
                    }
                    callback.geocodedResultsReceived(ers, pStatus);
                    return;
                }
            }
        }
        callback.geocodedResultsReceived(new GeocodingResult[]{}, pStatus);
    }
}

package com.framework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * 属性集合
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValues = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv){
        this.propertyValues.add(pv);
    }

    public PropertyValue[] getPropertyValues(){
        return this.propertyValues.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValues(String propertyName){
        for (PropertyValue pv : propertyValues){
            if (pv.getName().equals(propertyName)){
                return pv;
            }
        }
        return null;
    }

}

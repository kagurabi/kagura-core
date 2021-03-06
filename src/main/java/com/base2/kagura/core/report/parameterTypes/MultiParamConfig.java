/*
   Copyright 2014 base2Services

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package com.base2.kagura.core.report.parameterTypes;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;
import java.util.List;

/**
 * A parameter with a list of strings as the backing value. Allows for selection of multiple values from a combobox.
 * PropertyUtils from the Apache Commons Bean library is used to populate this value.
 * * User: aubels
 * Date: 17/07/13
 * Time: 2:55 PM
 */
@ApiModel(parent = ParamConfig.class, description = "A parameter that alllows multiple string values.")
public class MultiParamConfig extends ParamConfig {
    /**
     * Constructor with lots of helpers.
     * {@inheritDoc}
     */
    public MultiParamConfig(String name, String type, Collection list) {
        super(name, type, list);
    }

    /**
     * Protected constructor for deserialization
     */
    protected MultiParamConfig() {
    }

    List<String> value;

    /**
     * Gets the value.
     * @return
     */
	@ApiModelProperty(value = "A an array or list of strings. Can be null.")
	public List<String> getValue() {
        return value;
    }

    /**
     * Sets the value
     * @param value
     */
    public void setValue(List<String> value) {
        this.value = value;
    }
}

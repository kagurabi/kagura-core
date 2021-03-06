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
package com.base2.kagura.core.report.configmodel;

import com.base2.kagura.core.report.configmodel.parts.ColumnDef;
import com.base2.kagura.core.report.connectors.ReportConnector;
import com.base2.kagura.core.report.parameterTypes.ParamConfig;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

import java.util.List;
import java.util.Map;

/**
 * The base report configuration. Some of the fields are GIFO for the purpose of Kagura.js. some fields, particularly
 * on in child classes are required for the generation of the report. All these fields are designed to be deserialized
 * from a JSON file, then safely serialized again into a JSON message for REST communication with Kagura.js. When
 * creating new reports backends please bare this in mind.
 * User: aubels
 * Date: 24/07/13
 * Time: 4:46 PM
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type")
@JsonTypeIdResolver(ReportConfigTypeResolver.class)
public abstract class ReportConfig {
    String reportId;
    List<ParamConfig> paramConfig;
    List<ColumnDef> columns;
    Map<String, String> extraOptions;
    private Integer pageLimit;

    /**
     * The parameter configuration.
     * @return
     */
    public List<ParamConfig> getParamConfig() {
        return paramConfig;
    }

	/**
	 * @see #getParamConfig()
	 */
	public void setParamConfig(List<ParamConfig> paramConfig) {
		this.paramConfig = paramConfig;
	}

    /**
     * Provides the report connector for the engine. The driver of the report. Use this to execute the report.
     * @return
     */
    @JsonIgnore
    abstract public ReportConnector getReportConnector();

    /**
     * The report identifier, namely the containing directory if using the @see FileConfigProvider
     * @return
     */
    @JsonIgnore
    public java.lang.String getReportId() {
        return reportId;
    }

	/**
	 * @see #getReportId()
	 */
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

    /**
     * The report type identifier
     * @return
     */
    @JsonIgnore
    abstract public java.lang.String getReportType();

    /**
     * Get the column configuration of the report. This is GIGO from the report configuration.
     * @return
     */
    public List<ColumnDef> getColumns() {
        return columns;
    }

    /**
     * @see #getColumns()
     */
    public void setColumns(List<ColumnDef> columns) {
        this.columns = columns;
    }

    /**
     * Extra options specific to the report, designed to be passed to kagura.js.
     * This is GIGO from the report configuration.
     * @return
     */
    public Map<String, String> getExtraOptions() {
        return extraOptions;
    }

    /**
     * @see #getExtraOptions()
     */
    public void setExtraOptions(Map<String, String> extraOptions) {
        this.extraOptions = extraOptions;
    }

    /**
     * Overwrites the page limit in the report. This defaults to 10 or 20.
     * @return
     */
    public Integer getPageLimit() {
        return pageLimit;
    }

    /**
     * @see #getPageLimit()
     */
    public void setPageLimit(Integer pageLimit) {
        this.pageLimit = pageLimit;
    }

    /**
     * Prepares the parameters for the report. This populates the Combo and ManyCombo box options if there is a groovy
     * or report backing the data.
     * @param extra Extra options from the middleware, this is passed down to the report or groovy execution.
     */
    public void prepareParameters(Map<String, Object> extra) {
        if (paramConfig == null) return;
        for (ParamConfig param : paramConfig)
        {
            param.prepareParameter(extra);
        }
    }
}

/*
 * Copyright 2014 Black Pepper Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.blackpepper.support.spring.context;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.validation.MapBindingResult;

public final class BindingResultUtils2 extends BindingResultUtils {
	
	private BindingResultUtils2() {
		throw new AssertionError();
	}
	
	public static BindingResult newGlobalErrors(String errorCode) {
		BindingResult errors = new MapBindingResult(new HashMap<>(), "");
		errors.reject(errorCode);
		return errors;
	}
	
	public static Map<String, Object> setBindingResult(Map<String, Object> model, BindingResult bindingResult) {
		Assert.notNull(model, "Model map must not be null");
		Assert.notNull(bindingResult, "BindingResult must not be null");
		Assert.notNull(bindingResult.getObjectName(), "BindingResult objectName must not be null");
		model.put(BindingResult.MODEL_KEY_PREFIX + bindingResult.getObjectName(), bindingResult);
		return model;
	}
}

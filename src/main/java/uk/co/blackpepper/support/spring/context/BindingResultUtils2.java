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

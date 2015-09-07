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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.validation.BindingResult;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BindingResultUtils2Test {

	private ExpectedException thrown = ExpectedException.none();
	
	@Rule
	public ExpectedException getThrown() {
		return thrown;
	}
	
	// TODO: remove when code grows to exceed coverage ratio or constructor can be ignored
	@Test(expected = InvocationTargetException.class)
	public void constructorIsPrivate() throws NoSuchMethodException, InstantiationException, IllegalAccessException,
		InvocationTargetException {
		Constructor<BindingResultUtils2> constructor = BindingResultUtils2.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		constructor.newInstance();
	}
	
	@Test
	public void newGlobalErrorsReturnsBindingResult() {
		BindingResult bindingResult = BindingResultUtils2.newGlobalErrors("x");
		
		assertThat("objectName", bindingResult.getObjectName(), isEmptyString());
		assertThat("globalError", bindingResult.getGlobalError().getCode(), is("x"));
	}
	
	@Test
	public void setBindingResultSetsBindingResult() {
		Map<String, Object> model = new HashMap<>();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.getObjectName()).thenReturn("x");
		
		BindingResultUtils2.setBindingResult(model, bindingResult);
		
		assertThat((BindingResult) model.get("org.springframework.validation.BindingResult.x"), is(bindingResult));
	}
	
	@Test
	public void setBindingResultReturnsBindingResult() {
		Map<String, Object> model = new HashMap<>();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.getObjectName()).thenReturn("x");
		
		Map<String, Object> actual = BindingResultUtils2.setBindingResult(model, bindingResult);
		
		assertThat(actual, is(sameInstance(model)));
	}
	
	@Test
	public void setBindingWithNullModelThrowsException() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Model map must not be null");
		
		BindingResultUtils2.setBindingResult(null, mock(BindingResult.class));
	}
	
	@Test
	public void setBindingWithNullBindingResultThrowsException() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("BindingResult must not be null");
		
		BindingResultUtils2.setBindingResult(new HashMap<String, Object>(), null);
	}
	
	@Test
	public void setBindingWithBindingResultWithNullObjectNameThrowsException() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("BindingResult objectName must not be null");
		
		BindingResultUtils2.setBindingResult(new HashMap<String, Object>(), mock(BindingResult.class));
	}
}

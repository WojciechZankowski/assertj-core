/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2016 the original author or authors.
 */
package org.assertj.core.api.predicate;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;

import org.assertj.core.api.PredicateAssert;
import org.assertj.core.api.PredicateAssertBaseTest;
import org.assertj.core.util.Sets;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.error.ElementsShouldMatch.elementsShouldMatch;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.assertj.core.util.Lists.newArrayList;
import static org.mockito.Mockito.verify;

/**
 * @author Filip Hrisafov
 */
public class PredicateAssert_acceptsAll_Test extends PredicateAssertBaseTest {

  @Test
  public void should_fail_when_predicate_is_null() {
    thrown.expectAssertionError(actualIsNull());
    LinkedHashSet<String> acceptedValues = Sets.newLinkedHashSet("first", "second");

    assertThat((Predicate<String>) null).acceptsAll(acceptedValues);
  }

  @Test
  public void should_fail_when_predicate_does_not_accept_values() {
    Set<String> acceptedValues = Sets.newLinkedHashSet("first", "second");
    Predicate<String> predicate = acceptedValues::contains;
    Set<String> matchValues = Sets.newHashSet(acceptedValues);
    matchValues.add("third");
    thrown.expectAssertionError(elementsShouldMatch(matchValues, "third", predicate).create());
    assertThat(predicate).acceptsAll(matchValues);
  }

  @Test
  public void should_pass_when_predicate_accepts_all_values() {
    Set<String> acceptedValues = Sets.newLinkedHashSet("first", "second");
    Predicate<String> predicate = acceptedValues::contains;
    assertThat(predicate).acceptsAll(acceptedValues);
  }

  @Override
  protected PredicateAssert<Boolean> invoke_api_method() {
    return assertions.acceptsAll(newArrayList(true, true));
  }

  @Override
  protected void verify_internal_effects() {
    verify(iterables).assertAllMatch(getInfo(assertions), newArrayList(true, true), getActual(assertions));
  }
}
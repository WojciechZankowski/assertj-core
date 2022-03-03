/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2022 the original author or authors.
 */
package org.assertj.core.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.util.DateUtil.dayOfMonthOf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

/**
 * Tests for <code>{@link DateUtil#dayOfMonthOf(Date)}</code>.
 * 
 * @author Joel Costigliola
 */
class DateUtil_dayOfMonthOf_Test {

  @Test
  void should_return_day_of_month_of_date() throws ParseException {
    String dateAsString = "26/08/1994";
    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateAsString);
    assertThat(dayOfMonthOf(date)).isEqualTo(26);
  }

  @Test
  void should_throws_NullPointerException_if_date_parameter_is_null() {
    assertThatNullPointerException().isThrownBy(() -> dayOfMonthOf(null));
  }

}

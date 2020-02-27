/*
 * Copyright 2019 Sonu Kumar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.sonus21.rqueue.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * Check whether {@link RedisMessageListenerContainer} bean exists in the bean factory or not. If it
 * does not exist then application would create the container automatically.
 */
public class MissingRedisMessageListener implements Condition {

  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    try {
      if (context.getBeanFactory() != null) {
        context.getBeanFactory().getBean(RedisMessageListenerContainer.class);
        return false;
      }
      return true;
    } catch (BeansException e) {
      return true;
    }
  }
}
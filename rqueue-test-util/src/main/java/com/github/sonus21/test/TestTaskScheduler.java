/*
 * Copyright (c) 2021-2023 Sonu Kumar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 *
 */

package com.github.sonus21.test;

import java.time.Instant;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

public class TestTaskScheduler extends ThreadPoolTaskScheduler {

  private static final long serialVersionUID = 3617860362304703358L;
  public boolean shutdown = false;
  List<Future<?>> tasks = new Vector<>();

  public TestTaskScheduler() {
    setPoolSize(1);
    afterPropertiesSet();
  }

  @Override
  public Future<?> submit(Runnable r) {
    Future<?> f = super.submit(r);
    tasks.add(f);
    return f;
  }

  @Override
  public ScheduledFuture<?> schedule(Runnable r, Instant instant) {
    ScheduledFuture<?> f = super.schedule(r, instant);
    tasks.add(f);
    return f;
  }

  public int submittedTasks() {
    return tasks.size();
  }

  @Override
  public void shutdown() {
    super.shutdown();
    shutdown = true;
  }
}

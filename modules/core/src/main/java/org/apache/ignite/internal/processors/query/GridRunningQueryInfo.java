/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.internal.processors.query;

/**
 * Query descriptor.
 */
public class GridRunningQueryInfo {
    /** */
    private final long id;

    /** */
    private final String qry;

    /** */
    private final String cache;

    /** */
    private final long startTime;

    /** */
    private final GridQueryCancel cancel;

    /**
     * @param id Query ID.
     * @param qry Query text.
     * @param cache Cache where query was executed.
     * @param startTime Query start time.
     * @param cancel Query cancel.
     */
    public GridRunningQueryInfo(Long id, String qry, String cache, long startTime, GridQueryCancel cancel) {
        this.id = id;
        this.qry = qry;
        this.cache = cache;
        this.startTime = startTime;
        this.cancel = cancel;
    }

    /**
     * @return Query ID.
     */
    public Long id() {
        return id;
    }

    /**
     * @return Query text.
     */
    public String query() {
        return qry;
    }

    /**
     * @return Cache where query was executed.
     */
    public String cache() {
        return cache;
    }

    /**
     * @return Query start time.
     */
    public long startTime() {
        return startTime;
    }

    /**
     * @param curTime Current time.
     * @param duration Duration of long query.
     * @return {@code true} if this query should be considered as long running query.
     */
    public boolean longQuery(long curTime, long duration) {
        return curTime - startTime > duration;
    }

    /**
     * Cancel query.
     */
    public void cancel() {
        if (cancel != null)
            cancel.cancel();
    }
}

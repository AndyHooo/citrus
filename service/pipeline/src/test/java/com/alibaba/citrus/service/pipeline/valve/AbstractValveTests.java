/*
 * Copyright 2010 Alibaba Group Holding Limited.
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.alibaba.citrus.service.pipeline.valve;

import static com.alibaba.citrus.generictype.TypeInfoUtil.*;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;

import com.alibaba.citrus.service.pipeline.AbstractPipelineTests;
import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.service.pipeline.Valve;

public abstract class AbstractValveTests<V extends Valve> extends AbstractPipelineTests {
    protected Class<V> valveClass;
    protected V valve;
    protected PipelineContext pipelineContext;

    @BeforeClass
    public static void initFactory() throws Exception {
        createFactory("services-valves.xml");
    }

    @Before
    @SuppressWarnings("unchecked")
    public final void init() {
        valveClass = (Class<V>) resolveParameter(getClass(), AbstractValveTests.class, 0).getRawType();
        valve = newInstance();
        pipelineContext = createMock(PipelineContext.class);
    }

    protected final V newInstance() {
        try {
            return valveClass.newInstance();
        } catch (Exception e) {
            fail(e.getMessage());
            return null;
        }
    }
}

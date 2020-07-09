/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.myfaces.core.extensions.quarkus.runtime.scopes;

import java.lang.annotation.Annotation;

import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.CDI;
import javax.faces.view.ViewScoped;

import org.apache.myfaces.cdi.view.ViewScopeContext;

import io.quarkus.arc.InjectableContext;

public class QuarkusViewScopeContext implements InjectableContext
{
    private ViewScopeContext wrapped;

    public QuarkusViewScopeContext()
    {
    }

    public ViewScopeContext getWrapped()
    {
        if (wrapped == null)
        {
            wrapped = new ViewScopeContext(CDI.current().getBeanManager());
        }
        return wrapped;
    }

    @Override
    public void destroy()
    {

    }

    @Override
    public void destroy(Contextual<?> contextual)
    {
        getWrapped().destroy(contextual);
    }

    @Override
    public Class<? extends Annotation> getScope()
    {
        return ViewScoped.class;
    }

    @Override
    public <T> T get(Contextual<T> contextual, CreationalContext<T> cc)
    {
        return getWrapped().get(contextual, cc);
    }

    @Override
    public <T> T get(Contextual<T> contextual)
    {
        return getWrapped().get(contextual);
    }

    @Override
    public boolean isActive()
    {
        return getWrapped().isActive();
    }

    @Override
    public ContextState getState()
    {
        return null;
    }
}

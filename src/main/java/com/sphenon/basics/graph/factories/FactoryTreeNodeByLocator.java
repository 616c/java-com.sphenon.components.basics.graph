package com.sphenon.basics.graph.factories;

/****************************************************************************
  Copyright 2001-2024 Sphenon GmbH

  Licensed under the Apache License, Version 2.0 (the "License"); you may not
  use this file except in compliance with the License. You may obtain a copy
  of the License at http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
  License for the specific language governing permissions and limitations
  under the License.
*****************************************************************************/

import com.sphenon.basics.context.*;
import com.sphenon.basics.context.classes.*;
import com.sphenon.basics.cache.*;
import com.sphenon.basics.exception.*;
import com.sphenon.basics.configuration.*;
import com.sphenon.basics.message.*;
import com.sphenon.basics.notification.*;
import com.sphenon.basics.customary.*;
import com.sphenon.basics.locating.*;
import com.sphenon.basics.services.*;

import com.sphenon.basics.graph.*;

import java.util.Vector;

public class FactoryTreeNodeByLocator implements Consumer<SpecificFactoryTreeNodeByLocator> {

    protected FactoryTreeNodeByLocator(CallContext context) {
    }

    static protected FactoryTreeNodeByLocator factory;

    static public synchronized FactoryTreeNodeByLocator get(CallContext context) {
        if (factory == null) {
            factory = new FactoryTreeNodeByLocator(context);
        }
        return factory;
    }

    public void notifyNewService(CallContext context, SpecificFactoryTreeNodeByLocator service) {
        // nice to see you
    }

    public Class<SpecificFactoryTreeNodeByLocator> getServiceClass(CallContext context) {
        return SpecificFactoryTreeNodeByLocator.class;
    }

    public TreeNode tryCreate(CallContext context, Locator locator) {
        Vector<SpecificFactoryTreeNodeByLocator> factories = ServiceRegistry.getConsumerServices(context,  this);

        if (factories == null) {
            return null;
        }

        String lci = locator.getLocatorClassId(context);

        for (SpecificFactoryTreeNodeByLocator factory : factories) {
            if (factory.getLocatorClassId(context).equals(lci)) {
                return factory.tryCreate(context, locator);
            }
        }

        return null;
    }
}

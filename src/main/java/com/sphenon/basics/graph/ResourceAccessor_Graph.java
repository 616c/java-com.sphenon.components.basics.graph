package com.sphenon.basics.graph;

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
import com.sphenon.basics.message.*;
import com.sphenon.basics.exception.*;
import com.sphenon.basics.notification.*;
import com.sphenon.basics.customary.*;
import com.sphenon.basics.services.*;
import com.sphenon.basics.validation.returncodes.*;

import com.sphenon.basics.graph.*;
import com.sphenon.basics.graph.factories.*;

import java.util.Vector;

public class ResourceAccessor_Graph implements ResourceAccessor {

    public ResourceAccessor_Graph (CallContext context) {
    }

    public void notifyNewConsumer(CallContext context, Consumer consumer) {
        // nice to see you
    }

    public boolean equals(Object object) {
        return (object instanceof ResourceAccessor_Graph);
    }

    public Vector<String> read(CallContext context, String locator) {
        TreeLeaf tl = null;
        try {
            tl = (TreeLeaf) Factory_TreeNode.construct(context, locator, NodeType.LEAF, false);
        } catch (ValidationFailure vf) {
            CustomaryContext.create((Context)context).throwPreConditionViolation(context, vf, "Cannot find resource to read from '%(locator)'", "locator", locator);
            throw (ExceptionPreConditionViolation) null; // compiler insists
        }
        return NodeUtilities.readLeaf(context, tl);
    }
}

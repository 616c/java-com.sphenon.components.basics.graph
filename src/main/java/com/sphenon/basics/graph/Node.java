package com.sphenon.basics.graph;

/****************************************************************************
  Copyright 2001-2018 Sphenon GmbH

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
import com.sphenon.basics.message.*;
import com.sphenon.basics.notification.*;
import com.sphenon.basics.customary.*;
import com.sphenon.basics.locating.*;

import com.sphenon.ui.core.*;
import com.sphenon.ui.annotations.*;

@UIId         ("js:instance.getId(context)")
@UIName       ("js:instance.getId(context)")
@UIClassifier ("Node")
public interface Node {

    /**
       Get id of node, unique within naming scope of parent.
     */
    public String   getId(CallContext context);

    /**
       Get location of node.
     */
    public Location getLocation(CallContext context);

    /**
       Get last modification of this TreeNode as a long value, comparable to
       standard java system time values.

       @returns The time of last modification, or 0 if no such time is determinable.
    */
    public long getLastModification(CallContext context);
}

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

import com.sphenon.basics.graph.tplinst.*;

/**
   Used by the getChild method of TreeNode to select childs.

   Please note: TreeFilter shall never change their behaviour after
   creation. Their identity is used for caching purposes. I.e., filter results
   are not recalculated if the same filter instance is used.
*/
public interface NodeFilter {

    /**
       This method it is Invoked for each child and if this method returns true, the child is included in the result.
       @param node The node to test.
       @return true, if and only if the child is to be included in the result
    */
    public boolean matches(CallContext context, TreeNode node);
}

package com.sphenon.basics.graph.classes;

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
import com.sphenon.basics.exception.*;
import com.sphenon.basics.message.*;
import com.sphenon.basics.notification.*;
import com.sphenon.basics.customary.*;
import com.sphenon.basics.validation.returncodes.*;
import com.sphenon.basics.locating.*;
import com.sphenon.basics.locating.factories.*;

import com.sphenon.basics.graph.*;
import com.sphenon.basics.graph.tplinst.*;
import com.sphenon.basics.graph.files.factories.*;

import java.io.*;
import java.util.HashMap;

abstract public class TreeNode_BaseImpl implements TreeNode {

    public TreeNode_BaseImpl (CallContext context) {
    }

    protected java.util.WeakHashMap<NodeFilter,Vector_TreeNode_long_> filter_results;

    public Vector_TreeNode_long_ getChilds(CallContext context, NodeFilter filter) {
        return getChilds(context, filter, null);
    }

    public Vector_TreeNode_long_ getChilds(CallContext context, NodeFilter filter, TreeNode_Filter tn_filter) {
        Vector_TreeNode_long_ all = this.getChilds(context);
        if (filter == null) { return all; }
        Vector_TreeNode_long_ result = null;
        if (tn_filter != null) {
            if (this.filter_results == null) {
                this.filter_results = new java.util.WeakHashMap<NodeFilter,Vector_TreeNode_long_>();
            }
            result = this.filter_results.get(filter);
        }
        if (result == null) {
            result = Factory_Vector_TreeNode_long_.construct(context);
            for (TreeNode tn : new VectorIterable_TreeNode_long_(context, all)) {
                if (filter.matches(context, tn)) {
                    if (tn_filter == null || tn instanceof TreeLeaf) {
                        result.append(context, tn);
                    } else {
                        result.append(context, new TreeNode_Filter(context, tn, filter, tn_filter));
                    }
                }
            }
            if (tn_filter != null) {
                this.filter_results.put(filter, result);
            }
        }
        return result;
    }

    public TreeNode getChildTree(CallContext context, NodeFilter filter) {
        return new TreeNode_Filter(context, this, filter, null);
    }

    public String optionallyGetLinkTarget(CallContext context) {
        return null;
    }
}

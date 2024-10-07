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

import java.util.HashMap;
import java.util.Vector;

public class TreeNode_Sorted extends TreeNode_BaseImpl {

    protected TreeNode node;
    protected boolean recursive;
    
    public TreeNode_Sorted (CallContext context, TreeNode node) {
        this(context, node, false);
    }

    public TreeNode_Sorted (CallContext context, TreeNode node, boolean recursive) {
        super(context);
        this.node = node;
        this.recursive = recursive;
    }

    public String getId(CallContext context) {
        return this.node.getId(context);
    }

    public Location getLocation(CallContext context) {
        return this.node.getLocation(context);
    }

    public String getPath(CallContext context) {
        return this.node.getPath(context);
    }

    public TreeNode tryGetChild(CallContext context, String id) {
        return this.node.tryGetChild(context, id);
    }

    public TreeNode tryGetOrCreateChild(CallContext context, String id, NodeType node_type) {
        TreeNode child = this.node.tryGetOrCreateChild(context, id, node_type);
        if (child != null) {
            childs_updated = -1;
        }
        return child;
    }

    public long getLastModification(CallContext context) {
        return this.node.getLastModification(context);
    }

    public TreeNode tryGetParent(CallContext context) {
        return this.node.tryGetParent(context);
    }

    public boolean exists(CallContext context) {
        return this.node.exists(context);
    }

    protected Vector_TreeNode_long_ childs;
    protected long childs_updated = -1;

    public Vector_TreeNode_long_ getChilds(CallContext context) {
        long lm = this.getLastModification(context);
        if (this.childs_updated == -1 || lm > this.childs_updated) {
            this.childs = Factory_Vector_TreeNode_long_.construct(context);
            this.childs_updated = lm;
            this.filter_results = null;
            for (TreeNode child : this.node.getChilds(context).getIterable_TreeNode_(context)) {
                this.childs.append(context, this.recursive && ! (child instanceof TreeLeaf) ? (new TreeNode_Sorted(context, child, this.recursive)) : child);
            }
            java.util.Collections.sort(((VectorImpl_TreeNode_long_)(this.childs)).getImplementationVector(context), new NodeComparator(context));
        }
        return this.childs;
    }

    protected class NodeComparator implements java.util.Comparator {
        protected CallContext context;
        public NodeComparator(CallContext context) {
            this.context = context;
        }
        public int compare(Object o1, Object o2) {
            TreeNode n1 = (TreeNode) o1;
            TreeNode n2 = (TreeNode) o2;
            String id1 = n1.getId(context);
            String id2 = n2.getId(context);
            return id1.compareTo(id2);
        }
    }

    public String optionallyGetLinkTarget(CallContext context) {
        return this.node.optionallyGetLinkTarget(context);
    }
}

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

public class TreeNode_Filter extends TreeNode_BaseImpl {

    protected TreeNode   node;
    protected NodeFilter filter;
    protected TreeNode   parent;
    
    public TreeNode_Filter (CallContext context, TreeNode node, NodeFilter filter, TreeNode parent) {
        super(context);
        this.node   = node;
        this.filter = filter;
        this.parent = parent;
    }

    public NodeFilter getFilter(CallContext context) {
        return this.filter;
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
        String[] parts = id.split("/", 2);
        id = parts[0];
        TreeNode child_node = this.getChildMap(context).get(id);
        return (child_node == null ? null : (parts.length == 1 ? child_node : child_node.tryGetChild(context, parts[1])));
    }

    public TreeNode tryGetOrCreateChild(CallContext context, String id, NodeType node_type) {
        return this.tryGetChild(context, id);
    }

    public long getLastModification(CallContext context) {
        return this.node.getLastModification(context);
    }

    public TreeNode tryGetParent(CallContext context) {
        return this.parent;
    }

    protected HashMap<String,TreeNode> child_map;
    protected Vector_TreeNode_long_ childs;

    protected HashMap<String,TreeNode> getChildMap(CallContext context) {
        Vector_TreeNode_long_ childs = this.getChilds(context);
        if (this.child_map == null) {
            this.child_map = new HashMap<String,TreeNode>();
        }
        if (childs != null) {
            for (TreeNode child : childs.getIterable_TreeNode_(context)) {
                this.child_map.put(child.getId(context), child);
            }
        }
        return this.child_map;
    }

    public Vector_TreeNode_long_ getChilds(CallContext context) {
        if (this.childs == null) {
            this.childs = this.node.getChilds(context, this.filter, this);
        }
        return this.childs;
    }

    public boolean exists(CallContext context) {
        return this.node.exists(context);
    }

    public String optionallyGetLinkTarget(CallContext context) {
        return this.node.optionallyGetLinkTarget(context);
    }
}

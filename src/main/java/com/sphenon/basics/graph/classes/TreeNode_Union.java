package com.sphenon.basics.graph.classes;

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
import java.util.List;
import java.util.ArrayList;

public class TreeNode_Union extends TreeNode_BaseImpl {

    protected List<TreeNode> nodes;
    protected TreeNode       parent;
    protected boolean        tolerate_leaf_conflicts;
    
    public TreeNode_Union (CallContext context, TreeNode parent, String id, TreeNode... nodes) {
        this(context, parent, id, false, nodes);
    }

    public TreeNode_Union (CallContext context, TreeNode parent, String id, boolean tolerate_leaf_conflicts, TreeNode... nodes) {
        this(context, parent, id, tolerate_leaf_conflicts, new ArrayList<TreeNode>());
        if (nodes != null) {
            for (TreeNode node : nodes) {
                this.nodes.add(node);
            }
        }
    }

    public TreeNode_Union (CallContext context, TreeNode parent, String id, List<TreeNode> nodes) {
        this(context, parent, id, false, nodes);
    }

    public TreeNode_Union (CallContext context, TreeNode parent, String id, boolean tolerate_leaf_conflicts, List<TreeNode> nodes) {
        super(context);
        this.parent = parent;
        this.id = id;
        this.nodes = nodes;
        this.tolerate_leaf_conflicts = tolerate_leaf_conflicts;
    }

    public void addNode (CallContext context, TreeNode node) {
        this.nodes.add(node);
        if (this.parent == null) {
            this.id = null;
        }
    }

    protected String id;

    public String getId(CallContext context) {
        return this.id;
    }

    public Location getLocation(CallContext context) {
        return null;
    }

    public String getPath(CallContext context) {
        return (this.parent == null ? "" : (this.parent.getPath(context) + "/")) + this.getId(context);
    }

    public TreeNode tryGetChild(CallContext context, String id) {
        return null;
    }

    public TreeNode tryGetOrCreateChild(CallContext context, String id, NodeType node_type) {
        return this.tryGetChild(context, id);
    }

    public long getLastModification(CallContext context) {
        long lm = -1L;
        for (TreeNode node : nodes) {
            long lmcur = node.getLastModification(context);
            if (lmcur > lm) { lm = lmcur; }
        }
        return lm;
    }

    public TreeNode tryGetParent(CallContext context) {
        return this.parent;
    }

    protected HashMap<String,TreeNode> child_map;
    protected Vector_TreeNode_long_ childs;
    protected long childs_updated = -1;

    protected HashMap<String,TreeNode> getChildMap(CallContext context) {
        if (this.child_map == null) {
            this.child_map = new HashMap<String,TreeNode>();
        }
        return this.child_map;
    }

    public Vector_TreeNode_long_ getChilds(CallContext context) {
        long lm = this.getLastModification(context);
        if (this.childs_updated == -1 || lm > this.childs_updated) {
            this.childs = Factory_Vector_TreeNode_long_.construct(context);
            this.child_map = null;
            this.childs_updated = lm;
            this.filter_results = null;
            for (TreeNode node : nodes) {
                for (TreeNode child : node.getChilds(context).getIterable_TreeNode_(context)) {
                    TreeNode map_child = this.getChildMap(context).get(child.getId(context));
                    if (map_child == null) {
                        if (child instanceof TreeLeaf) {
                            this.childs.append(context, child);
                            this.getChildMap(context).put(child.getId(context), child);
                        } else {
                            map_child = new TreeNode_Union(context, this, child.getId(context), this.tolerate_leaf_conflicts, child);
                            this.childs.append(context, map_child);
                            this.getChildMap(context).put(child.getId(context), map_child);
                        }
                    } else {
                        if (child instanceof TreeLeaf || map_child instanceof TreeLeaf) {
                            if (this.tolerate_leaf_conflicts == false) {
                                CustomaryContext.create((Context)context).throwConfigurationError(context, "While merging treenodes, the nodes with id '(%id)' exists more than once and at least one occurence is a leaf (1st location: '%(first)', 2nd location '%(second)')", "id", child.getId(context), "first", child.getLocation(context), "second", map_child.getLocation(context));
                                throw (ExceptionConfigurationError) null; // compiler insists
                            }
                        } else {
                            ((TreeNode_Union) map_child).addNode(context, child);
                        }
                    }
                }
            }
        }
        return this.childs;
    }

    public boolean exists(CallContext context) {
        for (TreeNode node : nodes) {
            if (node.exists(context)) { return true; }
        }
        return false;
    }
}

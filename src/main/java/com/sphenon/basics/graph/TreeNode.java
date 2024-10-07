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
import com.sphenon.basics.message.*;
import com.sphenon.basics.notification.*;
import com.sphenon.basics.customary.*;

import com.sphenon.basics.graph.tplinst.*;
import com.sphenon.basics.graph.classes.*;

import com.sphenon.ui.core.*;
import com.sphenon.ui.annotations.*;

@UIParts      ( { "js:instance.getChilds(context).getIterable(context)"
                } )
public interface TreeNode extends Node {

    /**
       Get path from tree root to this node.
    */
    public String getPath(CallContext context);

    /**
       Get parent node with respect to tree structure.
    */
    public TreeNode tryGetParent(CallContext context);

    /**
       Try to get a child by it's id.
       @return the child with the given id, or null if no such child exists
    */
    public TreeNode tryGetChild(CallContext context, String id);

    /**
       Try to get a child by it's id; if it does not exist, try to create it

       @param id           id of existing or new child
       @param node_type    used if and only if a new child is created
       @return the child with the given id, or null if no such child exists and it cannot be created
    */
    public TreeNode tryGetOrCreateChild(CallContext context, String id, NodeType node_type);

    /**
       Get all childs.
    */
    public Vector_TreeNode_long_ getChilds(CallContext context);

    /**
       Get all childs that match the criteria defined by the given node filter.
       @param filter an instance of NodeFilter that is used to check each child whether it's included in the result or whether not.
                     If null, this function acts as getChilds without filter argument.
       @return a vector containing the selected childs
    */
    public Vector_TreeNode_long_ getChilds(CallContext context, NodeFilter filter);

    /**
       Get all childs that match the criteria defined by the given node
       filter. Additionally, all nodes that are not leaves, are wrapped into a
       TreeNode_Filter, i.e. accessing them applies the same filter to all
       childs recursively.

       In other words, this method returns a complete filtered subtree.

       @param filter an instance of NodeFilter that is used to check each child whether it's included in the result or whether not.
                     If null, this function acts as getChilds without filter argument.
       @return a TreeNode containing the selected childs, including the
               complete filtered subtree
    */
    public TreeNode getChildTree(CallContext context, NodeFilter filter);

    /**
       Internal method, required to implement getChildTree. Works like
       getChilds without tn_filter argument, and returns the same list of
       TreeNodes, but nodes that are not leaves are additionally wrapped
       within TreeNode_Filter instancess with the same internal filter.
    */
    public Vector_TreeNode_long_ getChilds(CallContext context, NodeFilter filter, TreeNode_Filter tn_filter);

    /**
       Checks, whether the item denoted by this node exists.

       @return true, if the item exists
    */
    public boolean exists(CallContext context);

    /**
       Checks, whether the item denoted by this node is a symbolic link,
       and if so, returns the link target.

       @return link target, if item is symbolic link, otherwise null
    */
    public String optionallyGetLinkTarget(CallContext context);
}

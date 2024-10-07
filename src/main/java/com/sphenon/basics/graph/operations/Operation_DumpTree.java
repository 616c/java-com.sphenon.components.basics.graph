package com.sphenon.basics.graph.operations;

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
import com.sphenon.basics.notification.*;
import com.sphenon.basics.customary.*;
import com.sphenon.basics.locating.*;
import com.sphenon.basics.locating.classes.*;
import com.sphenon.basics.monitoring.*;
import com.sphenon.basics.operations.*;
import com.sphenon.basics.operations.classes.*;
import com.sphenon.basics.operations.factories.*;
import com.sphenon.basics.data.*;

import com.sphenon.basics.graph.*;
import com.sphenon.basics.graph.tplinst.*;
import com.sphenon.basics.graph.classes.*;
import com.sphenon.basics.graph.factories.*;

import java.io.File;

public class Operation_DumpTree implements Operation {

    public Operation_DumpTree (CallContext context) {
    }

    protected TreeNode tree_node;

    public TreeNode getTreeNode (CallContext context) {
        return this.tree_node;
    }

    public void setTreeNode (CallContext context, TreeNode tree_node) {
        this.tree_node = tree_node;
    }

    public Execution execute (CallContext context) {
        return execute(context, null);
    }

    protected void dumpFolder(CallContext context, TreeNode tree_node, String indent) {
        if (tree_node == null) {
            System.err.println(indent + "Node: does not exist");
        } else {
            System.err.println(indent + "Node: " + tree_node.getId(context) + " - " + tree_node.getClass().getName().replaceFirst(".*\\.",""));
            if ((tree_node instanceof TreeLeaf) == false) {
                for (TreeNode child : tree_node.getChilds(context).getIterable_TreeNode_(context)) {
                    dumpFolder(context, child, indent + "  ");
                }
            }
        }
    }

    public Execution execute (CallContext context, DataSink<Execution> execution_sink) {
        Execution execution = null;
        try {
            dumpFolder(context, this.tree_node, "");

            execution = Factory_Execution.createExecutionSuccess(context);
        } catch (Throwable t) {
            execution = Factory_Execution.createExecutionFailure(context, t);
        }

        if (execution_sink != null) { execution_sink.set(context, execution); }
        return execution;
    }
}


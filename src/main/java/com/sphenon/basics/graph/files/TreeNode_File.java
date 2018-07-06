package com.sphenon.basics.graph.files;

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
import com.sphenon.basics.expression.*;
import com.sphenon.basics.validation.returncodes.*;
import com.sphenon.basics.locating.*;
import com.sphenon.basics.locating.factories.*;

import com.sphenon.basics.graph.*;
import com.sphenon.basics.graph.tplinst.*;
import com.sphenon.basics.graph.classes.*;
import com.sphenon.basics.graph.files.factories.*;

import java.io.*;
import java.util.HashMap;

public class TreeNode_File extends TreeNode_BaseImpl {

    protected File     file;
    protected TreeNode parent;
    protected Location location;
    
    static protected RegularExpression global_file_exclude = new RegularExpression("^(?:\\.#.*)|(?:.*~)$");

    protected TreeNode_File (CallContext context, File file, TreeNode parent) {
        super(context);
        this.file = file;
        this.parent = parent;
    }

    static public TreeNode_File create (CallContext context, File file, TreeNode parent) {
        return create (context, file, parent, true);
    }

    static public TreeNode_File create (CallContext context, File file, TreeNode parent, boolean allow_new) {
        if (file.isDirectory() || (allow_new && file.exists() == false)) {
            return new TreeNode_File(context, file, parent);
        }
        CustomaryContext.create((Context)context).throwPreConditionViolation(context, "File '%(file)' argument to TreeNode_File is not a folder", "file", file);
        throw (ExceptionPreConditionViolation) null; // compiler insists
    }

    public String getId(CallContext context) {
        return this.file.getName();
    }

    public Location getLocation(CallContext context) {
        if (this.location == null) {
            String locator = "ctn://File/" + this.file.getPath();
            try {
                this.location = Factory_Location.construct(context, Factory_Locator.construct(context, locator));
            } catch (ValidationFailure vf) {
                CustomaryContext.create((Context)context).throwAssertionProvedFalse(context, vf, "Internally created file locator '%(locator)' is invalid", "locator", locator);
                throw (ExceptionAssertionProvedFalse) null; // compiler insists
            }
        }
        return this.location;
    }

    public String getPath(CallContext context) {
        return this.file.getPath();
    }

    public TreeNode tryGetChild(CallContext context, String id) {
        String[] parts = id.split("/", 2);
        id = parts[0];
        TreeNode child_node = this.getChildMap(context).get(id);
        if (child_node == null) {
            child_node = Factory_TreeNode_File.tryConstruct(context, new File(this.file, id));
            if (child_node != null) {
                this.getChildMap(context).put(id, child_node);
            }
        }
        return (child_node == null ? null : (parts.length == 1 ? child_node : child_node.tryGetChild(context, parts[1])));
    }

    public TreeNode tryGetOrCreateChild(CallContext context, String id, NodeType node_type) {
        TreeNode child = this.tryGetChild(context, id);
        if (child == null) {
            try {
                child = Factory_TreeNode_File.construct(context, new File(this.file, id), node_type, true);
            } catch (ValidationFailure vf) {
                CustomaryContext.create((Context)context).throwAssertionProvedFalse(context, vf, "Could not create child node (unexpectedly)");
                throw (ExceptionAssertionProvedFalse) null; // compiler insists
            }
            if (this.childs != null) {
                this.childs.append(context, child);
                this.getChildMap(context).put(id, child);
            }
        }
        return child;
    }

    public long getLastModification(CallContext context) {
        return this.file.lastModified();
    }

    public TreeNode tryGetParent(CallContext context) {
        if (this.parent == null) {
            File parent_file = this.file.getParentFile();
            try {
                this.parent = (parent_file == null ? null : Factory_TreeNode_File.construct(context, parent_file, (TreeNode) null));
            } catch (ValidationFailure vf) {
                CustomaryContext.create((Context)context).throwAssertionProvedFalse(context, vf, "Could not create parent node (unexpectedly)");
                throw (ExceptionAssertionProvedFalse) null; // compiler insists
            }
        }
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
        if (this.childs_updated == -1 || this.file.exists() == false || this.file.lastModified() > this.childs_updated) {
            this.childs = Factory_Vector_TreeNode_long_.construct(context);
            if (file.exists() == false) {
                this.childs_updated = -1;
            } else {
                this.childs_updated = this.file.lastModified();
                File[] child_array = this.file.listFiles();
                this.filter_results = null;
                for (File child : child_array) {
                    String child_name = child.getName();
                    if (global_file_exclude.matches(context, child_name)) {
                        continue;
                    }
                    TreeNode child_node = this.getChildMap(context).get(child_name);
                    if (child_node == null) {
                        try {
                            child_node = Factory_TreeNode_File.construct(context, child, this);
                        } catch (ValidationFailure vf) {
                            CustomaryContext.create((Context)context).throwAssertionProvedFalse(context, vf, "Could not create child node (unexpectedly)");
                            throw (ExceptionAssertionProvedFalse) null; // compiler insists
                        }
                        this.getChildMap(context).put(child.getName(), child_node);
                    }
                    this.childs.append(context, child_node);
                }
            }
        }
        return this.childs;
    }

    public boolean exists(CallContext context) {
        return this.file.exists();
    }
}

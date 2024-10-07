package com.sphenon.basics.graph.objects;

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
import com.sphenon.basics.encoding.*;
import com.sphenon.basics.validation.returncodes.*;
import com.sphenon.basics.locating.*;
import com.sphenon.basics.locating.factories.*;
import com.sphenon.basics.system.*;
import com.sphenon.basics.expression.*;
import com.sphenon.basics.data.*;
import com.sphenon.basics.graph.*;
import com.sphenon.basics.graph.classes.*;
import com.sphenon.basics.graph.factories.*;
import com.sphenon.basics.graph.tplinst.*;

import java.io.*;

import java.util.Vector;
import java.util.HashMap;

public class TreeNode_Object extends TreeNode_BaseImpl implements TreeLeaf {

    static final public Class _class = TreeNode_Object.class;

    static protected long notification_level;
    static public    long adjustNotificationLevel(long new_level) { long old_level = notification_level; notification_level = new_level; return old_level; }
    static public    long getNotificationLevel() { return notification_level; }
    static { notification_level = NotificationLocationContext.getLevel(_class); };

    protected Object          instance;
    protected String          id;
    protected TreeNode_Object parent;

    protected TreeNode_Object (CallContext context, Object instance, TreeNode_Object parent) {
        super(context);
        this.instance   = instance;
        this.id         = (instance == null ? "null" : SystemUtilities.getObjectIdHex(context, instance));
        this.parent     = parent;
    }

    static public TreeNode_Object create (CallContext context, Object instance, TreeNode_Object parent) {
        return new TreeNode_Object(context, instance, parent);
    }

    static public TreeNode_Object create (CallContext context, Object instance) {
        return new TreeNode_Object(context, instance, null);
    }

    public String getId(CallContext context) {
        return this.id;
    }

    public Location getLocation(CallContext context) {
        return null;
    }

    public String getPath(CallContext context) {
        return (this.parent == null ? "" : (this.parent.getPath(context) + "/")) + (this.id == null ? "" : this.id);
    }

    public String getPathEncoded(CallContext context) {
        return (this.parent == null ? "" : (this.parent.getPath(context) + "/")) + Encoding.recode(context, this.id, Encoding.UTF8, Encoding.URI);
    }

    public TreeNode tryGetChild(CallContext context, String id) {
        String[] parts = id.split("/", 2);
        id = parts[0];
        this.getChilds(context);
        // [ToDo] (possibly properties of this instance)
        TreeNode child_node = null;
        return (child_node == null ? null : (parts.length == 1 ? child_node : child_node.tryGetChild(context, parts[1])));
    }

    public TreeNode tryGetOrCreateChild(CallContext context, String id, NodeType node_type) {
        return this.tryGetChild(context, id);
    }

    public long getLastModification(CallContext context) {
        return 0L; // this.file.lastModified();
    }

    public TreeNode tryGetParent(CallContext context) {
        return this.parent;
    }

    protected Vector_TreeNode_long_ childs;

    public Vector_TreeNode_long_ getChilds(CallContext context) {
        if (this.childs == null) {
            this.childs = Factory_Vector_TreeNode_long_.construct(context);
        }
        return this.childs;
    }

    public boolean exists(CallContext context) {
        return true;
    }

    public String optionallyGetLinkTarget(CallContext context) {
        return null;
    }

    public NodeContent getContent(CallContext context) {
        return new NodeContent_Data() {
            public Data getData(CallContext context) {
                return Data_MediaObject_String.create(context, ContextAware.ToString.convert(context, instance), null);
            }
        };
    }
}

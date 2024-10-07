package com.sphenon.basics.graph.io;

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
import com.sphenon.basics.data.*;
import com.sphenon.basics.locating.*;
import com.sphenon.basics.locating.factories.*;

import com.sphenon.basics.graph.*;
import com.sphenon.basics.graph.tplinst.*;
import com.sphenon.basics.graph.classes.*;
import com.sphenon.basics.graph.files.factories.*;

import java.io.*;
import java.util.HashMap;

public class TreeNode_MediaObject implements TreeNode {

    protected TreeNode parent;
    protected Data_MediaObject data__media_object;
    
    protected TreeNode_MediaObject (CallContext context, Data_MediaObject data__media_object, TreeNode parent) {
        this.data__media_object = data__media_object;
        this.parent = parent;
    }

    public String getId(CallContext context) {
        Locator locator = this.data__media_object.tryGetOrigin(context);
        String id = (locator != null ? locator.getTextLocator(context)
                                     : this.data__media_object.getDispositionFilename(context));
        return id;
    }

    public Location getLocation(CallContext context) {
        Locator locator = this.data__media_object.tryGetOrigin(context);
        return locator == null ? null : Factory_Location.construct(context, locator);
    }

    public String getPath(CallContext context) {
        return "<MediaObject>/" + this.getId(context);
    }

    public TreeNode tryGetChild(CallContext context, String id) {
        return null;
    }

    public TreeNode tryGetOrCreateChild(CallContext context, String id, NodeType node_type) {
        return this.tryGetChild(context, id);
    }

    public long getLastModification(CallContext context) {
        return this.data__media_object.getLastUpdate(context).getTime();
    }

    public TreeNode tryGetParent(CallContext context) {
        return null;
    }

    public Vector_TreeNode_long_ getChilds(CallContext context) {
        return null;
    }

    public Vector_TreeNode_long_ getChilds(CallContext context, NodeFilter filter) {
        return null;
    }

    public Vector_TreeNode_long_ getChilds(CallContext context, NodeFilter filter, TreeNode_Filter tn_filter) {
        return null;
    }

    public TreeNode getChildTree(CallContext context, NodeFilter filter) {
        return null;
    }

    public boolean exists(CallContext context) {
        return true;
    }

    public String optionallyGetLinkTarget(CallContext context) {
        return null;
    }
}

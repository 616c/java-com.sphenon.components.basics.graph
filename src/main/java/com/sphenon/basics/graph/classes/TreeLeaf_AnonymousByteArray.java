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
import com.sphenon.basics.data.*;
import com.sphenon.basics.metadata.*;
import com.sphenon.basics.locating.*;

import com.sphenon.basics.graph.*;
import com.sphenon.basics.graph.tplinst.*;

import java.io.*;
import java.util.Date;

public class TreeLeaf_AnonymousByteArray implements TreeLeaf {
    
    protected byte[]   ba;
    protected Type     media_type;
    protected Date     created;
    protected Location location;

    protected TreeLeaf_AnonymousByteArray (CallContext context, byte[] ba, Type media_type, Location location) {
        this.ba = ba;
        this.media_type = media_type;
        this.created = new java.util.Date();
        this.location = location;
    }

    static public TreeLeaf_AnonymousByteArray create (CallContext context, byte[] ba, Type media_type, Location location) {
        return new TreeLeaf_AnonymousByteArray(context, ba, media_type, location);
    }

    public String getId(CallContext context) {
        return "--anonymous--";
    }

    public Location getLocation(CallContext context) {
        return location;
    }

    public String getPath(CallContext context) {
        return "--anonymous--";
    }

    public TreeNode tryGetChild(CallContext context, String id) {
        return null;
    }

    public TreeNode tryGetOrCreateChild(CallContext context, String id, NodeType node_type) {
        return this.tryGetChild(context, id);
    }

    public long getLastModification(CallContext context) {
        return this.created.getTime();
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

    public NodeContent getContent(CallContext context) {
        return new NodeContent_Data() {
                       public Data getData(CallContext context) {
                           return Data_MediaObject_Stream.create(context, new ByteArrayInputStream(ba), media_type, "--anonymous--", "--anonymous--");
                       }
                   };
    }

    public boolean exists(CallContext context) {
        return true;
    }
}

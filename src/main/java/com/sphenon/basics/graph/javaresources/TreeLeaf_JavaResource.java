package com.sphenon.basics.graph.javaresources;

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
import com.sphenon.basics.data.*;
import com.sphenon.basics.metadata.*;

import com.sphenon.basics.graph.*;
import com.sphenon.basics.graph.tplinst.*;

import java.io.*;

public class TreeLeaf_JavaResource extends TreeNode_JavaResource implements TreeLeaf {

    protected TreeLeaf_JavaResource (CallContext context, TreeNode_JavaResource parent, String id, long last_modification) {
        super(context, parent, id, last_modification);
    }

    static public TreeNode_JavaResource create (CallContext context, TreeNode_JavaResource parent, String id, long last_modification) {
        return new TreeLeaf_JavaResource(context, parent, id, last_modification);
    }

    public Vector_TreeNode_long_ getChilds(CallContext context) {
        return null;
    }

    public TreeNode tryGetOrCreateChild(CallContext context, String id, NodeType node_type) {
        return null;
    }

    public NodeContent getContent(CallContext context) {
        return new NodeContent_Data() {
                       public Data getData(CallContext context) {
                           // [AnnoyingBugInTomcatURLClassLoader]
                           // "%xyz" throws exception cause of malformed url
                           // but "%25xyz" is not found, der saubazi
                           // String path_encoded = getPathEncoded(context);
                           String path = getPath(context);
                           InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path.substring(1));
                           return Data_MediaObject_Stream.create(context, is, TypeManager.getMediaTypeRoot(context), path, "ctn://JavaResource" + path);
                       }
                   };
    }

    public void clearEntriesOfClassLoader(CallContext context, String loader_id) {
        // got no childs and no caches
    }
}

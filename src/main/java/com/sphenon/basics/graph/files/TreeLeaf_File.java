package com.sphenon.basics.graph.files;

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

import com.sphenon.basics.graph.*;
import com.sphenon.basics.graph.tplinst.*;

import java.io.*;
import java.nio.file.*;

public class TreeLeaf_File extends TreeNode_File implements TreeLeaf {

    protected TreeLeaf_File (CallContext context, File file, TreeNode parent) {
        super(context, file, parent);
    }

    static public TreeLeaf_File create (CallContext context, File file, TreeNode parent) {
        return create (context, file, parent, false);
    }

    static public TreeLeaf_File create (CallContext context, File file, TreeNode parent, boolean allow_new) {
        if (    file.getName().startsWith("##FILE##")
             || file.isFile()
             || (allow_new && file.exists() == false)
             || Files.isSymbolicLink(file.toPath())
           ) {
            return new TreeLeaf_File(context, file, parent);
        }
        CustomaryContext.create((Context)context).throwPreConditionViolation(context, "File '%(file)' argument to TreeLeaf_File exists, but is not a file", "file", file);
        throw (ExceptionPreConditionViolation) null; // compiler insists
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
                           return Data_MediaObject_File.create(context, file, null);
                       }
                   };
    }
}

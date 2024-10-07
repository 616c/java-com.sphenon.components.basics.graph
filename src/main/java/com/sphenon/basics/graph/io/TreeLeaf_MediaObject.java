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
import com.sphenon.basics.data.*;

import com.sphenon.basics.graph.*;
import com.sphenon.basics.graph.tplinst.*;

import java.io.*;

public class TreeLeaf_MediaObject extends TreeNode_MediaObject implements TreeLeaf {

    protected TreeLeaf_MediaObject (CallContext context, Data_MediaObject data__media_object, TreeNode parent) {
        super(context, data__media_object, parent);
    }

    static public TreeLeaf_MediaObject create (CallContext context, Data_MediaObject data__media_object, TreeNode parent) {
        return new TreeLeaf_MediaObject(context, data__media_object, parent);
    }

    static public TreeLeaf_MediaObject create (CallContext context, String data, TreeNode parent) {
        return new TreeLeaf_MediaObject(context, Data_MediaObject_String.create(context, data, null), parent);
    }

    public NodeContent getContent(CallContext context) {
        return new NodeContent_Data() {
                       public Data getData(CallContext context) {
                           return data__media_object;
                       }
                   };
    }
}

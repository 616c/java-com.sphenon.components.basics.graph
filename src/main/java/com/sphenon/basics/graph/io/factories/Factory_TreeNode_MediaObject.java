package com.sphenon.basics.graph.io.factories;

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
import com.sphenon.basics.context.classes.*;
import com.sphenon.basics.exception.*;
import com.sphenon.basics.message.*;
import com.sphenon.basics.notification.*;
import com.sphenon.basics.customary.*;
import com.sphenon.basics.configuration.*;
import com.sphenon.basics.factory.*;
import com.sphenon.basics.data.*;
import com.sphenon.basics.validation.returncodes.*;

import com.sphenon.basics.graph.*;
import com.sphenon.basics.graph.tplinst.*;
import com.sphenon.basics.graph.io.*;

import java.io.*;

public class Factory_TreeNode_MediaObject implements Factory {

    /* -------------- extensible factory instantiation --------------------------------------------------------------------------------------- */
    static protected FactoryInstantiator<Factory_TreeNode_MediaObject> factory_instantiator;
    static {
      CallContext context = RootContext.getInitialisationContext();
      factory_instantiator = new FactoryInstantiator(context, Factory_TreeNode_MediaObject.class) { protected Factory_TreeNode_MediaObject createDefault(CallContext context) { return new Factory_TreeNode_MediaObject(context); } };
    };
    /* --------------------------------------------------------------------------------------------------------------------------------------- */
    static public Factory_TreeNode_MediaObject newInstance (CallContext context) {
        return factory_instantiator.newInstance(context);
    }
    /* --------------------------------------------------------------------------------------------------------------------------------------- */

    protected Factory_TreeNode_MediaObject (CallContext context) {
    }

    static public TreeNode_MediaObject construct (CallContext context, Data_MediaObject media_object) throws ValidationFailure {
        Factory_TreeNode_MediaObject factory = newInstance(context);
        factory.setMediaObject(context, media_object);
        factory.setParent(context, factory.defaultParent(context));
        return factory.create(context);
    }

    static public TreeNode_MediaObject tryConstruct (CallContext context, Data_MediaObject media_object) {
        Factory_TreeNode_MediaObject factory = newInstance(context);
        factory.setMediaObject(context, media_object);
        factory.setParent(context, factory.defaultParent(context));
        return factory.tryCreate(context);
    }

    static public TreeNode_MediaObject construct (CallContext context, Data_MediaObject media_object, TreeNode parent) throws ValidationFailure {
        Factory_TreeNode_MediaObject factory = newInstance(context);
        factory.setMediaObject(context, media_object);
        factory.setParent(context, parent);
        return factory.create(context);
    }

    static public TreeNode_MediaObject tryConstruct (CallContext context, Data_MediaObject media_object, TreeNode parent) {
        Factory_TreeNode_MediaObject factory = newInstance(context);
        factory.setMediaObject(context, media_object);
        factory.setParent(context, parent);
        return factory.tryCreate(context);
    }

    public TreeNode_MediaObject create (CallContext context) throws ValidationFailure {
        TreeNode_MediaObject tn = this.tryCreate(context);
        return tn;
    }

    public TreeNode_MediaObject tryCreate (CallContext context) {
        if (data != null) {
            media_object = Data_MediaObject_String.create(context, data, null);
        }
        return TreeLeaf_MediaObject.create(context, media_object, parent);
    }

    public Object createObject    (CallContext context) throws ValidationFailure {
        return create(context);
    }

    public void   reset           (CallContext context) {
    }

    /* --------------------------------------------------------------------------------------------------------------------------------------- */

    protected String data;

    public String getData (CallContext context) {
        return this.data;
    }

    public void setData (CallContext context, String data) {
        this.data = data;
    }

    public String defaultData (CallContext context) {
        return null;
    }

    protected Data_MediaObject media_object;

    public Data_MediaObject getMediaObject (CallContext context) {
        return this.media_object;
    }

    public void setMediaObject (CallContext context, Data_MediaObject media_object) {
        this.media_object = media_object;
    }

    protected TreeNode parent;

    public TreeNode getParent (CallContext context) {
        return this.parent;
    }

    public TreeNode defaultParent (CallContext context) {
        return null;
    }

    public void setParent (CallContext context, TreeNode parent) {
        this.parent = parent;
    }

    /* --------------------------------------------------------------------------------------------------------------------------------------- */

    public void   validate        (CallContext context) throws ValidationFailure {
    }

    /* --------------------------------------------------------------------------------------------------------------------------------------- */

    public void confirmAttributes (CallContext context) {
    }

    public void validateFinally   (CallContext context) throws ValidationFailure {
    }
}

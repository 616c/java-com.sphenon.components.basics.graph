// instantiated with jti.pl from WriteVector

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
// please do not modify this file directly
package com.sphenon.basics.graph.tplinst;

import com.sphenon.basics.graph.*;

import com.sphenon.basics.context.*;
import com.sphenon.basics.exception.*;

import com.sphenon.basics.many.returncodes.*;

public interface WriteVector_Vector_TreeNode_long__long_
{
    public Vector_TreeNode_long_ set          (CallContext context, long index, Vector_TreeNode_long_ item);
    public void     add          (CallContext context, long index, Vector_TreeNode_long_ item) throws AlreadyExists;
    public void     prepend      (CallContext context, Vector_TreeNode_long_ item);
    public void     append       (CallContext context, Vector_TreeNode_long_ item);
    public void     insertBefore (CallContext context, long index, Vector_TreeNode_long_ item) throws DoesNotExist;
    public void     insertBehind (CallContext context, long index, Vector_TreeNode_long_ item) throws DoesNotExist;
    public Vector_TreeNode_long_ replace      (CallContext context, long index, Vector_TreeNode_long_ item) throws DoesNotExist;
    public Vector_TreeNode_long_ unset        (CallContext context, long index);
    public Vector_TreeNode_long_ remove       (CallContext context, long index) throws DoesNotExist;
}


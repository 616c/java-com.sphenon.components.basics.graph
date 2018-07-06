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

public interface WriteVector_TreeNode_long_
{
    public TreeNode set          (CallContext context, long index, TreeNode item);
    public void     add          (CallContext context, long index, TreeNode item) throws AlreadyExists;
    public void     prepend      (CallContext context, TreeNode item);
    public void     append       (CallContext context, TreeNode item);
    public void     insertBefore (CallContext context, long index, TreeNode item) throws DoesNotExist;
    public void     insertBehind (CallContext context, long index, TreeNode item) throws DoesNotExist;
    public TreeNode replace      (CallContext context, long index, TreeNode item) throws DoesNotExist;
    public TreeNode unset        (CallContext context, long index);
    public TreeNode remove       (CallContext context, long index) throws DoesNotExist;
}


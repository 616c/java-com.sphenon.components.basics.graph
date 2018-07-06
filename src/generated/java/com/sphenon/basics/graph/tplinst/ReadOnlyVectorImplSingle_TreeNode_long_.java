// instantiated with jti.pl from ReadOnlyVectorImplSingle

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
import com.sphenon.basics.notification.*;
import com.sphenon.basics.customary.*;
import com.sphenon.basics.many.*;

import com.sphenon.basics.many.returncodes.*;

public class ReadOnlyVectorImplSingle_TreeNode_long_
  implements ReadOnlyVector_TreeNode_long_ {
    protected TreeNode item;

    public ReadOnlyVectorImplSingle_TreeNode_long_ (CallContext context, TreeNode item) {
        this.item = item;
    }

    public TreeNode get          (CallContext context, long index) throws DoesNotExist {
        if (index != 0) {
            DoesNotExist.createAndThrow (context);
            throw (DoesNotExist) null; // compiler insists
        }
        return item;
    }

    public TreeNode tryGet       (CallContext context, long index) {
        if (index != 0) {
            return null;
        }
        return item;
    }

    public boolean  canGet       (CallContext context, long index) {
        return (index == 0 ? true : false);
    }

    public IteratorItemIndex_TreeNode_long_ getNavigator (CallContext context) {
        return new VectorIteratorImpl_TreeNode_long_ (context, this);
    }

    public VectorReferenceToMember_TreeNode_long_ getReference (CallContext context, long index) throws DoesNotExist {
        if ( ! canGet(context, index)) {
            DoesNotExist.createAndThrow (context);
            throw (DoesNotExist) null; // compiler insists
        }
        return new VectorReferenceToMember_TreeNode_long_(context, this, 0L);
    }

    public VectorReferenceToMember_TreeNode_long_ tryGetReference (CallContext context, long index) {
        if ( ! canGet(context, index)) { return null; }
        return new VectorReferenceToMember_TreeNode_long_(context, this, 0L);
    }

    public long     getSize      (CallContext context) {
        return 1;
    }
}

// instantiated with jti.pl from VectorImplList

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
import com.sphenon.basics.debug.*;
import com.sphenon.basics.exception.*;
import com.sphenon.basics.notification.*;
import com.sphenon.basics.customary.*;
import com.sphenon.basics.many.*;

import com.sphenon.basics.many.returncodes.*;

public class VectorImplList_Vector_TreeNode_long__long_
  implements Vector_Vector_TreeNode_long__long_, VectorImplList, Dumpable, ManagedResource {
    protected java.util.List vector;

    protected VectorImplList_Vector_TreeNode_long__long_ (CallContext context) {
        vector = new java.util.ArrayList ();
    }

    static public VectorImplList_Vector_TreeNode_long__long_ create (CallContext context) {
        return new VectorImplList_Vector_TreeNode_long__long_(context);
    }

    protected VectorImplList_Vector_TreeNode_long__long_ (CallContext context, java.util.List vector) {
        this.vector = vector;
    }

    static public VectorImplList_Vector_TreeNode_long__long_ create (CallContext context, java.util.List vector) {
        return new VectorImplList_Vector_TreeNode_long__long_(context, vector);
    }

    public Vector_TreeNode_long_ get          (CallContext context, long index) throws DoesNotExist {
        try {
            return (Vector_TreeNode_long_) vector.get((int) index);
        } catch (IndexOutOfBoundsException e) {
            DoesNotExist.createAndThrow (context);
            throw (DoesNotExist) null; // compiler insists
        }
    }

    public Vector_TreeNode_long_ tryGet       (CallContext context, long index) {
        if (index < 0 || index >= vector.size()) {
            return null;
        }
        return (Vector_TreeNode_long_) vector.get((int) index);
    }

    public boolean  canGet       (CallContext context, long index) {
        return (index >= 0 && index < vector.size()) ? true : false;
    }

    public VectorReferenceToMember_Vector_TreeNode_long__long_ getReference    (CallContext context, long index) throws DoesNotExist {
        if ( ! canGet(context, index)) {
            DoesNotExist.createAndThrow (context);
            throw (DoesNotExist) null; // compiler insists
        }
        return new VectorReferenceToMember_Vector_TreeNode_long__long_(context, this, index);
    }

    public VectorReferenceToMember_Vector_TreeNode_long__long_ tryGetReference (CallContext context, long index) {
        if ( ! canGet(context, index)) { return null; }
        return new VectorReferenceToMember_Vector_TreeNode_long__long_(context, this, index);
    }

    public Vector_TreeNode_long_ set          (CallContext context, long index, Vector_TreeNode_long_ item) {
        while (index > vector.size()) { vector.add(null); }
        if( index == vector.size()) {
            vector.add(item);
            return null;
        } else {
            return (Vector_TreeNode_long_) vector.set((int) index, item);
        }
    }

    public void     add          (CallContext context, long index, Vector_TreeNode_long_ item) throws AlreadyExists {
        if (index < vector.size()) { AlreadyExists.createAndThrow (context); }
        set(context, index, item);
    }

    public void     prepend      (CallContext call_context, Vector_TreeNode_long_ item) {
        if (vector.size() == 0) {
            vector.add(item);
        } else {
            vector.add(0, item);
        }
    }

    public void     append       (CallContext context, Vector_TreeNode_long_ item) {
        vector.add(item);
    }

    public void     insertBefore (CallContext context, long index, Vector_TreeNode_long_ item) throws DoesNotExist {
        try {
            vector.add((int) index, item);
        } catch (IndexOutOfBoundsException e) {
            DoesNotExist.createAndThrow(context);
        }
    }

    public void     insertBehind (CallContext context, long index, Vector_TreeNode_long_ item) throws DoesNotExist {
        if (index == vector.size() - 1) {
            vector.add(item);
        } else {
            try {
                vector.add((int) index + 1, item);
            } catch (IndexOutOfBoundsException e) {
                DoesNotExist.createAndThrow (context);
            }
        }
    }

    public Vector_TreeNode_long_ replace      (CallContext call_context, long index, Vector_TreeNode_long_ item) throws DoesNotExist {
        try {
            return (Vector_TreeNode_long_) vector.set((int) index, item);
        } catch (IndexOutOfBoundsException e) {
            DoesNotExist.createAndThrow(call_context);
            throw (DoesNotExist) null;
        }
    }

    public Vector_TreeNode_long_ unset        (CallContext context, long index) {
        try {
            return (Vector_TreeNode_long_) vector.remove((int) index);
        } catch (IndexOutOfBoundsException e) {
            // we kindly ignore this exception
            return null;
        }
    }

    public Vector_TreeNode_long_ remove       (CallContext context, long index) throws DoesNotExist {
        try {
            return (Vector_TreeNode_long_) vector.remove((int) index);
        } catch (IndexOutOfBoundsException e) {
            DoesNotExist.createAndThrow (context);
            throw (DoesNotExist) null;
        }
    }

    public IteratorItemIndex_Vector_TreeNode_long__long_ getNavigator (CallContext context) {
        return new VectorIteratorImpl_Vector_TreeNode_long__long_ (context, this);
    }

    public long     getSize      (CallContext context) {
        return vector.size();
    }

    // to be used with care
    public java.util.List getImplementationList (CallContext context) {
        return this.vector;
    }

    public java.util.Iterator<Vector_TreeNode_long_> getIterator_Vector_TreeNode_long__ (CallContext context) {
        return vector.iterator();
    }

    public java.util.Iterator getIterator (CallContext context) {
        return getIterator_Vector_TreeNode_long__(context);
    }

    public VectorIterable_Vector_TreeNode_long__long_ getIterable_Vector_TreeNode_long__ (CallContext context) {
        return new VectorIterable_Vector_TreeNode_long__long_(context, this);
    }

    public Iterable<Vector_TreeNode_long_> getIterable (CallContext context) {
        return getIterable_Vector_TreeNode_long__ (context);
    }


    public void release(CallContext context) {
        if (this.vector != null && this.vector instanceof ManagedResource) {
            ((ManagedResource)(this.vector)).release(context);
        }
    }

    public void dump(CallContext context, DumpNode dump_node) {
        int i=1;
        for (Object o : vector) {
            dump_node.dump(context, (new Integer(i++)).toString(), o);
        }
    }
}

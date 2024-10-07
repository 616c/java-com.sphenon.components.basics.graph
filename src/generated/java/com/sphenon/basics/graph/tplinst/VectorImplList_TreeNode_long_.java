// instantiated with jti.pl from VectorImplList
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

public class VectorImplList_TreeNode_long_
  implements Vector_TreeNode_long_, VectorImplList, Dumpable, ManagedResource {
    protected java.util.List vector;

    protected VectorImplList_TreeNode_long_ (CallContext context) {
        vector = new java.util.ArrayList ();
    }

    static public VectorImplList_TreeNode_long_ create (CallContext context) {
        return new VectorImplList_TreeNode_long_(context);
    }

    protected VectorImplList_TreeNode_long_ (CallContext context, java.util.List vector) {
        this.vector = vector;
    }

    static public VectorImplList_TreeNode_long_ create (CallContext context, java.util.List vector) {
        return new VectorImplList_TreeNode_long_(context, vector);
    }

    public TreeNode get          (CallContext context, long index) throws DoesNotExist {
        try {
            return (TreeNode) vector.get((int) index);
        } catch (IndexOutOfBoundsException e) {
            DoesNotExist.createAndThrow (context);
            throw (DoesNotExist) null; // compiler insists
        }
    }

    public TreeNode tryGet       (CallContext context, long index) {
        if (index < 0 || index >= vector.size()) {
            return null;
        }
        return (TreeNode) vector.get((int) index);
    }

    public boolean  canGet       (CallContext context, long index) {
        return (index >= 0 && index < vector.size()) ? true : false;
    }

    public VectorReferenceToMember_TreeNode_long_ getReference    (CallContext context, long index) throws DoesNotExist {
        if ( ! canGet(context, index)) {
            DoesNotExist.createAndThrow (context);
            throw (DoesNotExist) null; // compiler insists
        }
        return new VectorReferenceToMember_TreeNode_long_(context, this, index);
    }

    public VectorReferenceToMember_TreeNode_long_ tryGetReference (CallContext context, long index) {
        if ( ! canGet(context, index)) { return null; }
        return new VectorReferenceToMember_TreeNode_long_(context, this, index);
    }

    public TreeNode set          (CallContext context, long index, TreeNode item) {
        while (index > vector.size()) { vector.add(null); }
        if( index == vector.size()) {
            vector.add(item);
            return null;
        } else {
            return (TreeNode) vector.set((int) index, item);
        }
    }

    public void     add          (CallContext context, long index, TreeNode item) throws AlreadyExists {
        if (index < vector.size()) { AlreadyExists.createAndThrow (context); }
        set(context, index, item);
    }

    public void     prepend      (CallContext call_context, TreeNode item) {
        if (vector.size() == 0) {
            vector.add(item);
        } else {
            vector.add(0, item);
        }
    }

    public void     append       (CallContext context, TreeNode item) {
        vector.add(item);
    }

    public void     insertBefore (CallContext context, long index, TreeNode item) throws DoesNotExist {
        try {
            vector.add((int) index, item);
        } catch (IndexOutOfBoundsException e) {
            DoesNotExist.createAndThrow(context);
        }
    }

    public void     insertBehind (CallContext context, long index, TreeNode item) throws DoesNotExist {
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

    public TreeNode replace      (CallContext call_context, long index, TreeNode item) throws DoesNotExist {
        try {
            return (TreeNode) vector.set((int) index, item);
        } catch (IndexOutOfBoundsException e) {
            DoesNotExist.createAndThrow(call_context);
            throw (DoesNotExist) null;
        }
    }

    public TreeNode unset        (CallContext context, long index) {
        try {
            return (TreeNode) vector.remove((int) index);
        } catch (IndexOutOfBoundsException e) {
            // we kindly ignore this exception
            return null;
        }
    }

    public TreeNode remove       (CallContext context, long index) throws DoesNotExist {
        try {
            return (TreeNode) vector.remove((int) index);
        } catch (IndexOutOfBoundsException e) {
            DoesNotExist.createAndThrow (context);
            throw (DoesNotExist) null;
        }
    }

    public IteratorItemIndex_TreeNode_long_ getNavigator (CallContext context) {
        return new VectorIteratorImpl_TreeNode_long_ (context, this);
    }

    public long     getSize      (CallContext context) {
        return vector.size();
    }

    // to be used with care
    public java.util.List getImplementationList (CallContext context) {
        return this.vector;
    }

    public java.util.Iterator<TreeNode> getIterator_TreeNode_ (CallContext context) {
        return vector.iterator();
    }

    public java.util.Iterator getIterator (CallContext context) {
        return getIterator_TreeNode_(context);
    }

    public VectorIterable_TreeNode_long_ getIterable_TreeNode_ (CallContext context) {
        return new VectorIterable_TreeNode_long_(context, this);
    }

    public Iterable<TreeNode> getIterable (CallContext context) {
        return getIterable_TreeNode_ (context);
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

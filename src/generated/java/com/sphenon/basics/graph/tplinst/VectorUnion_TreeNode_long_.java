// instantiated with jti.pl from VectorUnion
// please do not modify this file directly
package com.sphenon.basics.graph.tplinst;

import com.sphenon.basics.graph.*;

import com.sphenon.basics.context.*;
import com.sphenon.basics.exception.*;
import com.sphenon.basics.customary.*;
import com.sphenon.basics.many.*;
import com.sphenon.basics.message.*;
import com.sphenon.basics.many.returncodes.*;
// import com.sphenon.basics.event.*;
// import com.sphenon.basics.event.tplinst.*;

import com.sphenon.engines.aggregator.annotations.*;

public class VectorUnion_TreeNode_long_
  implements Vector_TreeNode_long_, ManagedResource
{
    protected Vector_Vector_TreeNode_long__long_ vectors;

    public VectorUnion_TreeNode_long_ (CallContext context) {
        this.vectors = null;
    }

    public VectorUnion_TreeNode_long_ (CallContext context, Vector_Vector_TreeNode_long__long_ vectors) {
        this.vectors = vectors;
    }

    public VectorUnion_TreeNode_long_ (CallContext context, Vector_TreeNode_long_... vectors) {
        this.vectors = Factory_Vector_Vector_TreeNode_long__long_.construct(context);
        for (Vector_TreeNode_long_ vector : vectors) {
            this.vectors.append(context, vector);
        }
    }

    public void setVectors (CallContext context, Vector_Vector_TreeNode_long__long_ vectors) {
        this.vectors = vectors;
    }

    @OCPIgnore
    public void setVectors (CallContext context, Vector_TreeNode_long_... vectors) {
        this.vectors = Factory_Vector_Vector_TreeNode_long__long_.construct(context);
        for (Vector_TreeNode_long_ vector : vectors) {
            this.vectors.append(context, vector);
        }
    }

    public void addVector(CallContext context, Vector_TreeNode_long_ vector) {
        if (this.vectors == null) {
            this.vectors = Factory_Vector_Vector_TreeNode_long__long_.construct(context);
        }
        this.vectors.append(context, vector);
    }

    public Vector_Vector_TreeNode_long__long_ getImplementationVector(CallContext context) {
        return this.vectors;
    }

    protected class SeekResult {
        public Vector_TreeNode_long_ vector;
        public long index;
    }

    protected SeekResult getVector (CallContext context, long index) {
        long offset = 0;
        SeekResult sr = new SeekResult();

        for (Vector_TreeNode_long_ vector : this.vectors.getIterable_Vector_TreeNode_long__(context)) {
            sr.vector = vector;
            if (sr.vector == null) { continue; }
            sr.index = index - offset;                

            long size = sr.vector.getSize(context);
            if (sr.index < size) {
                return sr;
            }
            offset += size;
        }
        return sr;
    }

    public TreeNode                                    get             (CallContext context, long index) throws DoesNotExist {
        SeekResult sr = this.getVector(context, index);
        if (sr.vector == null) { DoesNotExist.createAndThrow(context, "entry '%(index)'", "index", t.s(index)); }
        return sr.vector.get(context, sr.index);
    }

    public TreeNode                                    tryGet          (CallContext context, long index) {
        SeekResult sr = this.getVector(context, index);
        if (sr.vector == null) { return null; }
        return sr.vector.tryGet(context, sr.index);
    }

    public boolean                                     canGet          (CallContext context, long index) {
        SeekResult sr = this.getVector(context, index);
        if (sr.vector == null) { return false; }
        return sr.vector.canGet(context, sr.index);
    }

    public ReferenceToMember_TreeNode_long_ReadOnlyVector_TreeNode_long__ getReference    (CallContext context, long index) throws DoesNotExist {
        SeekResult sr = this.getVector(context, index);
        if (sr.vector == null) { DoesNotExist.createAndThrow(context, "entry '%(index)'", "index", t.s(index)); }
        return sr.vector.getReference(context, sr.index);
    }

    public ReferenceToMember_TreeNode_long_ReadOnlyVector_TreeNode_long__ tryGetReference (CallContext context, long index) {
        SeekResult sr = this.getVector(context, index);
        if (sr.vector == null) { return null; }
        return sr.vector.tryGetReference(context, sr.index);
    }

    public TreeNode                                    set             (CallContext context, long index, TreeNode item) {
        SeekResult sr = this.getVector(context, index);
        return sr.vector.set(context, sr.index, item);
    }

    public void                                        add             (CallContext context, long index, TreeNode item) throws AlreadyExists {
        SeekResult sr = this.getVector(context, index);
        sr.vector.add(context, sr.index, item);
    }

    public void                                        prepend         (CallContext context, TreeNode item) {
        this.vectors.tryGet(context, 0).prepend(context, item);
    }

    public void                                        append          (CallContext context, TreeNode item) {
        this.vectors.tryGet(context, this.vectors.getSize(context)-1).append(context, item);
    }

    public void                                        insertBefore    (CallContext context, long index, TreeNode item) throws DoesNotExist {
        SeekResult sr = this.getVector(context, index);
        sr.vector.insertBefore(context, sr.index, item);
    }

    public void                                        insertBehind    (CallContext context, long index, TreeNode item) throws DoesNotExist {
        SeekResult sr = this.getVector(context, index);
        sr.vector.insertBehind(context, sr.index, item);
    }

    public TreeNode                                    replace         (CallContext context, long index, TreeNode item) throws DoesNotExist {
        SeekResult sr = this.getVector(context, index);
        return sr.vector.replace(context, sr.index, item);
    }

    public TreeNode                                    unset           (CallContext context, long index) {
        SeekResult sr = this.getVector(context, index);
        return sr.vector.unset(context, sr.index);
    }

    public TreeNode                                    remove          (CallContext context, long index) throws DoesNotExist {
        SeekResult sr = this.getVector(context, index);
        return sr.vector.remove(context, sr.index);
    }

    public IteratorItemIndex_TreeNode_long_       getNavigator    (CallContext context) {
        return new VectorIteratorImpl_TreeNode_long_ (context, this);
    }

    public long                                        getSize         (CallContext context) {
        long size = 0;
        if (this.vectors != null) {
            for (long v=0; v<this.vectors.getSize(context); v++) {
                Vector_TreeNode_long_ vector = this.vectors.tryGet(context, v);
                if (vector != null) { size += vector.getSize(context); }
            }
        }
        return size;
    }

    protected class IteratorAdapter implements java.util.Iterator<TreeNode> {
        protected IteratorItemIndex_TreeNode_long_ iterator;
        protected CallContext context;
        public IteratorAdapter(CallContext context, IteratorItemIndex_TreeNode_long_ iterator) {
            this.iterator = iterator;
            this.context = context;
        }
        public boolean hasNext() { return iterator.canGetCurrent(this.context); }
        public void remove() { throw new UnsupportedOperationException(); }
        public TreeNode next() {
            if (iterator.canGetCurrent(this.context) == false) {
                throw new java.util.NoSuchElementException();
            }
            TreeNode current = iterator.tryGetCurrent(this.context);
            iterator.next(this.context);
            return current;
        }
    }

    public java.util.Iterator<TreeNode> getIterator_TreeNode_ (CallContext context) {
        return new IteratorAdapter(context, this.getNavigator(context));
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
        if (this.vectors != null && this.vectors instanceof ManagedResource) {
            ((ManagedResource)(this.vectors)).release(context);
        }
    }
}


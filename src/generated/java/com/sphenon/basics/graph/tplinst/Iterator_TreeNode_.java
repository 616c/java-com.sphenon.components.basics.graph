// instantiated with jti.pl from Iterator
// please do not modify this file directly
package com.sphenon.basics.graph.tplinst;

import com.sphenon.basics.graph.*;

import com.sphenon.basics.context.*;
import com.sphenon.basics.exception.*;

import com.sphenon.basics.many.returncodes.*;

public interface Iterator_TreeNode_
    extends com.sphenon.basics.many.Iterator<TreeNode>
{
    // advances iterator; if there is no next item iterator becomes invalid
    public void     next          (CallContext context);

    // returns current item; item must exist
    public TreeNode getCurrent    (CallContext context) throws DoesNotExist;

    // like "getCurrent", but returns null instead of throwing exception
    public TreeNode tryGetCurrent (CallContext context);

    // returns true if there is a current item available
    public boolean  canGetCurrent (CallContext context);

    // creates a clone of this iterator, pointing to exactly
    // the same position as yonder
    public Iterator_TreeNode_ clone(CallContext context);
}

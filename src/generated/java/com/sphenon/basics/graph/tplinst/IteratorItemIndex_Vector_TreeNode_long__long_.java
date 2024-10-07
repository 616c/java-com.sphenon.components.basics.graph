// instantiated with jti.pl from IteratorItemIndex
// please do not modify this file directly
package com.sphenon.basics.graph.tplinst;

import com.sphenon.basics.graph.*;

import com.sphenon.basics.context.*;
import com.sphenon.basics.exception.*;

import com.sphenon.basics.many.*;
import com.sphenon.basics.many.returncodes.*;

public interface IteratorItemIndex_Vector_TreeNode_long__long_
  extends Iterator_Vector_TreeNode_long__
    , IteratorItemIndex<Vector_TreeNode_long_>
{
    // returns current index
    public long getCurrentIndex (CallContext context) throws DoesNotExist;

    // like "getCurrentIndex", but returns null instead of throwing exception
    public long tryGetCurrentIndex (CallContext context);
}

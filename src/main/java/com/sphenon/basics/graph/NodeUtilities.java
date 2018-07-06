package com.sphenon.basics.graph;

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
import com.sphenon.basics.exception.*;
import com.sphenon.basics.context.classes.*;
import com.sphenon.basics.message.*;
import com.sphenon.basics.notification.*;
import com.sphenon.basics.customary.*;
import com.sphenon.basics.data.*;

import java.util.Vector;

import java.io.*;

public class NodeUtilities {

    static public Vector<String> tryReadLeaf(CallContext context, TreeLeaf tree_leaf) {
        return doReadLeaf(context, tree_leaf, false);
    }

    static public Vector<String> readLeaf(CallContext context, TreeLeaf tree_leaf) {
        return doReadLeaf(context, tree_leaf, true);
    }

    static public Vector<String> doReadLeaf(CallContext context, TreeLeaf tree_leaf, boolean throw_exception) {
        try {
            Data_MediaObject data = ((Data_MediaObject)(((NodeContent_Data)(tree_leaf.getContent(context))).getData(context)));
            InputStream is = null;
            is = (data instanceof Data_MediaObject_File ? new FileInputStream(((Data_MediaObject_File)(data)).getCurrentFile(context)) : data.getInputStream(context));

            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            Vector<String> lines = new Vector<String>();
            String line;
            while ((line = br.readLine()) != null) {
                int len = line.length();
                lines.add((len > 0 && line.charAt(len - 1) == '\n') ? line.substring(0, len - 1) : line);
            }

            br.close();
            isr.close();

            is.close();

            return lines;

        } catch (UnsupportedEncodingException uee) {
            if (throw_exception) {
                CustomaryContext.create((Context)context).throwPreConditionViolation(context, uee, "Cannot read from TreeNode '%(node)'", "node", tree_leaf.getId(context));
                throw (ExceptionPreConditionViolation) null; // compiler insists
            } else {
                return null;
            }
        } catch (IOException ioe) {
            if (throw_exception) {
                CustomaryContext.create((Context)context).throwPreConditionViolation(context, ioe, "Cannot read from TreeNode '%(node)'", "node", tree_leaf.getId(context));
                throw (ExceptionPreConditionViolation) null; // compiler insists
            } else {
                return null;
            }
        }
    }

    static public void tryWriteLeaf(CallContext context, TreeLeaf tree_leaf, String... lines) {
        doWriteLeaf(context, tree_leaf, false, false, lines);
    }

    static public void writeLeaf(CallContext context, TreeLeaf tree_leaf, String... lines) {
        doWriteLeaf(context, tree_leaf, true, false, lines);
    }

    static public void tryAppendToLeaf(CallContext context, TreeLeaf tree_leaf, String... lines) {
        doWriteLeaf(context, tree_leaf, false, true, lines);
    }

    static public void appendToLeaf(CallContext context, TreeLeaf tree_leaf, String... lines) {
        doWriteLeaf(context, tree_leaf, true, true, lines);
    }

    static public void doWriteLeaf(CallContext context, TreeLeaf tree_leaf, boolean throw_exception, boolean append, String... lines) {
        try {
            Data_MediaObject data = ((Data_MediaObject)(((NodeContent_Data)(tree_leaf.getContent(context))).getData(context)));
            OutputStream os = null;
            os = (data instanceof Data_MediaObject_File ? new FileOutputStream(((Data_MediaObject_File)(data)).getCurrentFile(context)) : data.getOutputStream(context));

            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            PrintWriter pw = new PrintWriter(bw);

            for (String line : lines) {
                pw.println(line);
            }

            pw.close();
            bw.close();
            osw.close();

            os.close();

        } catch (FileNotFoundException fnfe) {
            if (throw_exception) {
                CustomaryContext.create((Context)context).throwPreConditionViolation(context, fnfe, "Cannot write to TreeNode '%(node)'", "node", tree_leaf.getId(context));
                throw (ExceptionPreConditionViolation) null; // compiler insists
            }
        } catch (UnsupportedEncodingException uee) {
            if (throw_exception) {
                CustomaryContext.create((Context)context).throwPreConditionViolation(context, uee, "Cannot read from TreeNode '%(node)'", "node", tree_leaf.getId(context));
                throw (ExceptionPreConditionViolation) null; // compiler insists
            }
        } catch (IOException ioe) {
            if (throw_exception) {
                CustomaryContext.create((Context)context).throwPreConditionViolation(context, ioe, "Cannot read from TreeNode '%(node)'", "node", tree_leaf.getId(context));
                throw (ExceptionPreConditionViolation) null; // compiler insists
            }
        }
    }
}


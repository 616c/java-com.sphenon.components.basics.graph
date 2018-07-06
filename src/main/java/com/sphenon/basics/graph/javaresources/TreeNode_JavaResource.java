package com.sphenon.basics.graph.javaresources;

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
import com.sphenon.basics.configuration.*;
import com.sphenon.basics.customary.*;
import com.sphenon.basics.encoding.*;
import com.sphenon.basics.validation.returncodes.*;
import com.sphenon.basics.locating.*;
import com.sphenon.basics.locating.factories.*;
import com.sphenon.basics.system.*;
import com.sphenon.basics.expression.*;

import com.sphenon.basics.graph.*;
import com.sphenon.basics.graph.tplinst.*;
import com.sphenon.basics.graph.classes.*;
import com.sphenon.basics.graph.javaresources.factories.*;

import java.io.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.util.Vector;
import java.util.Date;
import java.util.HashMap;

public class TreeNode_JavaResource extends TreeNode_BaseImpl {

    static final public Class _class = TreeNode_JavaResource.class;

    static protected long notification_level;
    static public    long adjustNotificationLevel(long new_level) { long old_level = notification_level; notification_level = new_level; return old_level; }
    static public    long getNotificationLevel() { return notification_level; }
    static { notification_level = NotificationLocationContext.getLevel(_class); };

    static protected Configuration config;
    static { config = Configuration.create(RootContext.getInitialisationContext(), _class); };

    protected String                id;
    protected TreeNode_JavaResource parent;
    protected Location              location;
    protected long                  last_modification;

    protected TreeNode_JavaResource (CallContext context, TreeNode_JavaResource parent, String id, long last_modification) {
        super(context);
        this.id     = id;
        this.parent = parent;
        this.last_modification = last_modification;

        if ((notification_level & Notifier.DIAGNOSTICS) != 0) { NotificationContext.sendSelfDiagnostics(context, "New TreeNode_JavaResource: '%(path)'", "path", this.getPath(context)); }
    }

    static public TreeNode_JavaResource create (CallContext context, TreeNode_JavaResource parent, String id, long last_modification) {
        return new TreeNode_JavaResource(context, parent, id, last_modification);
    }

    public String getId(CallContext context) {
        return this.id;
    }

    public Location getLocation(CallContext context) {
        if (this.location == null) {
            String locator = "ctn://JavaResource/" + this.getPath(context);
            try {
                this.location = Factory_Location.construct(context, Factory_Locator.construct(context, locator));
            } catch (ValidationFailure vf) {
                CustomaryContext.create((Context)context).throwAssertionProvedFalse(context, vf, "Internally created java resource locator '%(locator)' is invalid", "locator", locator);
                throw (ExceptionAssertionProvedFalse) null; // compiler insists
            }
        }
        return this.location;
    }

    public String getPath(CallContext context) {
        return (this.parent == null ? "" : (this.parent.getPath(context) + "/")) + this.id;
    }

    public String getPathEncoded(CallContext context) {
        return (this.parent == null ? "" : (this.parent.getPath(context) + "/")) + Encoding.recode(context, this.id, Encoding.UTF8, Encoding.URI);
    }

    static protected long global_last_mod = -1;
    static volatile protected boolean last_mod_checked = false;

    static protected RegularExpression timestampre = new RegularExpression("#created: *(.*)");
    static protected DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    protected long parseDate(CallContext context, String date, String info) {
        if (date == null) {
            return -1;
        }
        try {
            Date d = fmt.parse(date);
            return d.getTime();
        } catch (ParseException pe) {
            CustomaryContext.create((Context)context).throwConfigurationError(context, pe, "Invalid date format in '%(info)'", "info", info);
            throw (ExceptionConfigurationError) null; // compiler insists
        }
    }

    public long getLastModification(CallContext context) {
        if (last_modification == -1 && last_mod_checked == false) {
            synchronized(TreeNode_JavaResource.class) {
                if (last_mod_checked == false) {
                    last_mod_checked = true;
                    Vector<String> entries = JarUtilities.tryReadResource(context, "/.index");
                    if (entries != null) {
                        for (String entry : entries) {
                            String[] match = timestampre.tryGetMatches(context, entry);
                            if (match != null) {
                                global_last_mod = parseDate(context, match[0], "entry 'Jar-File-Creation-Time' in jar MANIFEST");
                                break;
                            }
                        }
                    }
                }
            }
        }
        return last_modification != -1 ? last_modification : global_last_mod;
    }

    public TreeNode_JavaResource tryGetParent(CallContext context) {
        return this.parent;
    }

    protected class Cache {
        protected Vector_TreeNode_long_                             childs;
        protected java.util.Hashtable<String,TreeNode_JavaResource> child_hash;
        protected long                                              childs_updated = -1;
    }

    static protected String getCLId(CallContext context) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if (cl instanceof URLClassLoaderWithId) {
            return ((URLClassLoaderWithId) cl).getId(context);
        }
        return "";
    }

    protected HashMap<String,Cache> caches;

    protected Cache getCache(CallContext context) {
        if (caches == null) {
            caches = new HashMap<String,Cache>();
        }
        Cache c = caches.get(getCLId(context));
        if (c == null) {
            c = new Cache();
            caches.put(getCLId(context), c);
        }
        return c;
    }

    static protected class StaticCache {
        protected long           index_variants_updated;
        protected Vector<String> index_variants;
    }

    static protected HashMap<String,StaticCache> static_caches;

    static protected StaticCache getStaticCache(CallContext context) {
        StaticCache sc = null;
        if (static_caches == null) {
            static_caches = new HashMap<String,StaticCache>();
        } else {
            sc = static_caches.get(getCLId(context));
        }
        if (sc == null) {
            sc = new StaticCache();
            static_caches.put(getCLId(context), sc);
            sc.index_variants = new Vector<String>();
            String ivs = config.get(context, "IndexVariants", "");
            if ((notification_level & Notifier.DIAGNOSTICS) != 0) { NotificationContext.sendDiagnostics(context, "IndexVariants: '%(variants)'", "variants", ivs); }
            for (String iv : ivs.split(",")) {
                if (iv != null && iv.isEmpty() == false) { iv = "-" + iv; }
                sc.index_variants.add(iv);
            }
            sc.index_variants_updated = new java.util.Date().getTime();
        }
        return sc;
    }

    static public void addIndexVariant(CallContext context, String index_variant) {
        StaticCache sc = getStaticCache(context);
        index_variant = "-" + index_variant;
        boolean is_new = true;
        for (String iv : sc.index_variants) {
            if (iv.equals(index_variant)) {
                is_new = false;
                break;
            }
        }
        if (is_new) {
            sc.index_variants.add(index_variant);
        }
        sc.index_variants_updated = new java.util.Date().getTime();
    }

    public void clearEntriesOfClassLoader(CallContext context, String loader_id) {
        if ((notification_level & Notifier.DIAGNOSTICS) != 0) { NotificationContext.sendSelfDiagnostics(context, "Processing entries of '%(path)'", "path", this.getPath(context)); }
        if (this.caches != null) {
            java.util.Set<String> keys = caches.keySet();
            for (String key : keys) {
                if ((notification_level & Notifier.DIAGNOSTICS) != 0) { NotificationContext.sendSelfDiagnostics(context, "Processing loader cache '%(entry)'", "entry", key); }
                Cache c = caches.get(key);
                for (TreeNode tn : c.childs.getIterable_TreeNode_(context)) {
                    ((TreeNode_JavaResource) tn).clearEntriesOfClassLoader(context, loader_id);
                }
                if (key.equals(loader_id)) {
                    if ((notification_level & Notifier.DIAGNOSTICS) != 0) { NotificationContext.sendSelfDiagnostics(context, "Clearing cache of '%(path)' for loader '%(loader)'", "path", this.getPath(context), "loader", key); }
                    c.childs = null;
                    c.child_hash = null;
                    caches.remove(key);
                }
            }
        }
    }

    public TreeNode_JavaResource tryGetChild(CallContext context, String id) {
        String[] parts = id.split("/", 2);
        id = parts[0];
        this.getChilds(context);
        Cache c = getCache(context);
        TreeNode_JavaResource result = c.child_hash == null ? null : c.child_hash.get(id);
        return (result == null ? null : (parts.length == 1 ? result : result.tryGetChild(context, parts[1])));
    }

    public TreeNode tryGetOrCreateChild(CallContext context, String id, NodeType node_type) {
        return this.tryGetChild(context, id);
    }

    public Vector_TreeNode_long_ getChilds(CallContext context) {
        StaticCache sc = getStaticCache(context);
        Cache c = getCache(context);
        if (c == null || c.childs_updated == -1 || c.childs_updated < sc.index_variants_updated) {
            if ((notification_level & Notifier.DIAGNOSTICS) != 0) { NotificationContext.sendSelfDiagnostics(context, "Retrieving childs of TreeNode_JavaResource: '%(path)', cause: '%({'cache is empty','childs are outdated'}[cause])', class loader '%(loader)'", "path", this.getPath(context), "cause", c == null ? 0 : 1, "loader", this.getCLId(context)); }
            // [AnnoyingBugInTomcatURLClassLoader]
            // "%xyz" throws exception cause of malformed url
            // but "%25xyz" is not found, der saubazi
            // String path_encoded = getPathEncoded(context);
            String path = this.getPath(context);
            String index = (path + "/.index").substring(1);
            boolean first = true;
            for (String index_variant : sc.index_variants) {
                if ((notification_level & Notifier.DIAGNOSTICS) != 0) { NotificationContext.sendSelfDiagnostics(context, "Index variant: '%(variant)'", "variant", index_variant); }
                String index_file = index + index_variant;
                InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(index_file);
                if (is == null) {
                    if ((notification_level & Notifier.DIAGNOSTICS) != 0) { NotificationContext.sendSelfDiagnostics(context, "Index variant: '%(variant)' does not exist, resource path '%(path)'", "variant", index_variant, "path", index_file); }
                } else {
                    if (first) {
                        first = false;
                        c.childs = Factory_Vector_TreeNode_long_.construct(context);
                        c.child_hash = new java.util.Hashtable<String,TreeNode_JavaResource>();
                    }
                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                        String line;
                        while ((line = br.readLine()) != null) {
                            if (line.charAt(0) != '#') {
                                if ((notification_level & Notifier.DIAGNOSTICS) != 0) { NotificationContext.sendSelfDiagnostics(context, "Adding child: '%(child)'", "child", line); }
                                int pos = line.indexOf('#');
                                String child_id = pos == -1 ? line : line.substring(0, pos);
                                long child_last_mod = pos == -1 ? -1 :parseDate(context, line.substring(pos+1), "entry '" + line + "' in '" + index_file + "'");

                                TreeNode_JavaResource child = null;
                                if (child_id.matches(".*/$")) {
                                    child_id = child_id.substring(0,child_id.length()-1);
                                    child = TreeNode_JavaResource.create(context, this, child_id, child_last_mod);
                                } else {
                                    child = TreeLeaf_JavaResource.create(context, this, child_id, child_last_mod);
                                }
                                c.childs.append(context, child);
                                c.child_hash.put(child_id, child);
                            }
                        }
                        br.close();
                    } catch (UnsupportedEncodingException uee) {
                        CustomaryContext.create((Context)context).throwEnvironmentFailure(context, uee, "While reading java resource '%(packagepath)', something went wrong", "packagepath", path);
                        throw (ExceptionEnvironmentFailure) null; // compiler insists
                    } catch (IOException ioe) {
                        CustomaryContext.create((Context)context).throwEnvironmentFailure(context, ioe, "While reading java resource '%(packagepath)', something went wrong", "packagepath", path);
                        throw (ExceptionEnvironmentFailure) null; // compiler insists
                    }
                }
            }
            c.childs_updated = new java.util.Date().getTime();
        }
        return c.childs;
    }

    public boolean exists(CallContext context) {
        return true;
    }
}

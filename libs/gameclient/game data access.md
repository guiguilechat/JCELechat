Disclaimer
=

The result is not provided by CCP but by trial and torture from devs. It may be totally wrong at any time in the even closest future, it may already be obsolete at the time I am writing this.

This does not breach CCP's [EULA](https://support.eveonline.com/hc/en-us/articles/8413329735580-EVE-Online-End-User-License-Agreement) and we do not encourage to break it.

Especially this is not about the game in itself, but its public files, accessible without an access to the game

Purpose
=

This is about documenting the way to access and understand (part of) the game's public files. This access can allow to have more up-to-date, or more complete information about the game's mechanism and data.

Access
=

Client meta data
==

To get the client meta data, you can fetch the json from `https://binaries.eveonline.com/eveclient_TQ.json`

This json has a **buildnumber** field that is changed when the game files change, and that you need to know.

It also has a **protected** flag that implies a different access method and should void data fetching as long as this flag is true.

The date of export of this build can be extracted from this resource's `last-modified` header.

Root Index
==

with **buildnumber** set, the root index is available at `https://binaries.eveonline.com/eveonline_$(buildnumber).txt`

Indexes structure
==

Each index is a text file, with each line of it having the format  
`[server]:/[localpath],[remotepath],[md5],[size],[compressedsize]\(,[permissions])?`  
with :

 - **server** is either `app` for `https://binaries.eveonline.com` or `res` for `https://resources.eveonline.com`
 - **localpath** is the path in the cache to place the resource
 - **remotepath** is the path under the server tree to fetch the resource
 - **md5** and **size** allow to validate the resource, either in the cache or once fetched
 - the two other may be present or not, useful or not (I dont use them)

The root index contains several sub-index lines, with their localpath format `resfileindex[_flavor]?.txt` . Those provide additional resources depending on which flavor you use. In my case I use the vanilla one (so no flavor: `resfileindex.txt` )

Once you have your root index, your flavored sub index, you can cache the indexed resources locally, all at once or on-demand, to use them.

Interesting resources path and format
==

staticdata/
==

Some resources in that folder are static data for the game :

SqLite files
===

Those files are SQLite databases stored in the *staticdata/* dir. You can list them with `file staticdata/* | grep "SQLite" | cut -d':' -f1` which at the time resulted in :

>staticdata/blueprints.static  
staticdata/clonegrades.static  
staticdata/dbuffcollections.static  
staticdata/fighterabilitiesbytype.static  
staticdata/fighterabilities.static  
staticdata/industry_activities.static  
staticdata/industry_activity_modifier_sources.static  
staticdata/industry_activity_target_filters.static  
staticdata/industry_assembly_lines.static  
staticdata/industry_installation_types.static  
staticdata/infobubbles.static  
staticdata/skinlicenses.static  
staticdata/skinmaterials.static  
staticdata/skins.static

each database can be loaded in a sqlite, and has the following format :

 - one table called "cache" with
 - one column "key" which can be an object id
 - one column value that contains a json with table-specific structure
 - one column time that contains update time, as floating seconds since epoch (down to millisecond)

The structure of the internal json (in the following example the industry activity modifier sources) can be extracted with 

```bash
sqlite3 staticdata/industry_activity_modifier_sources.static <<EOF
select 
	tree.fullkey,
	tree.type
from
	cache c,
	json_tree(c.value) tree
WHERE
	not tree.fullkey regexp '.*\[[1-9].*' --ignore index more than 0
	and not tree.type in ('array', 'object')
group by tree.fullkey, tree.type
order by tree.fullkey, tree.type
EOF
```

In the same way this data can be converted with (in that case to show which types impact which activity)

```bash
sqlite3 -json staticdata/industry_activity_modifier_sources.static <<EOF
SELECT
        c.key type_id,
        trim(substr(activity.path,3, instr(substr(activity.path,3), '.')-1), '"') activity_name,        
        substr(activity.path,instr(substr(activity.path,3), '.')+3) modified,
        activity.value->>'dogmaAttributeID' modifying_attribute_id,
        activity.value->>'filterID' applied_filter_id
FROM
        cache c,
        json_tree(c.value) activity
where
        activity.path like '$.%.%'
        and activity.path not like '%]'
EOF
```

A [bash command](./sh/sqlstructure) loads all those sqlite files from the last cache in ./evecache and export the corresponding structure in [a sub directory](./structure/staticdata/)

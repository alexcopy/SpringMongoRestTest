var status = rs.status();
if (status.errmsg === 'no replset config has been received') {
    rs.initiate();
}
for (var i = 1; i <= param; i++) {
    if (i!==1)
        rs.add(folder+"_students-mongodb-node_" + i + ":30021");
}
cfg = rs.conf();
cfg.members[0].host = folder+"_students-mongodb-node_1:30021";
rs.reconfig(cfg);
        -- Setting and Confirming requireAuthentication
CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.connection.requireAuthentication', 'true');

        -- Setting authentication scheme to Derby
CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.authentication.provider', 'BUILTIN');

        -- Creating users
CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.user.admin', 'PIEUKJFD234J33DJFE');

        -- Setting default connection mode to no access
        -- (user authorization)
CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.database.defaultConnectionMode', 'noAccess');

        -- Defining read-write users
CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.database.fullAccessUsers', 'admin');

        -- We would set the following property to TRUE only
        -- when we were ready to deploy.
CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.database.propertiesOnly', 'false');


-- migration

update SAMPLEIN set refid = 190000000+mod(refid,1000000)

update SAMPLEOUT set refid = 190000000+mod(refid,1000000)

update SAMPLEIN_READING set id = 190000000+mod(id,1000000)

update SAMPLEOUT_READING set id = 190000000+mod(id,1000000)

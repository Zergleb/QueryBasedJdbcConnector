====================
Super Sink Connector
====================

.. code-block:: text

    com.caleb.kafka.connect.MySinkConnector

This is a description of this connector and will show up in the documentation


.. IMPORTANT::
    This is a important information that will show up in the documentation.


.. TIP::
    This is a tip that will show up in the documentation.


.. NOTE::
    This is a note that will show up in the documentation




-------------
Configuration
-------------
^^^^^^^
General
^^^^^^^


"""""""""""""""
jdbc.connection
"""""""""""""""

The jdbc connection URL to the database.

**Importance:** HIGH

**Type:** STRING



"""""""""""""""
jdbc.insertMode
"""""""""""""""

This says whether to use query or update to execute the query to execute the query (query can be needed when calling functions sometimes)

**Importance:** HIGH

**Type:** STRING



"""""""""""""
jdbc.password
"""""""""""""

The jdbc connection PASSWORD to the database.

**Importance:** HIGH

**Type:** STRING



""""""""""
jdbc.query
""""""""""

The actual query that will run, Place variables in according to sql2o documentation

**Importance:** HIGH

**Type:** STRING



"""""""""""""
jdbc.username
"""""""""""""

The jdbc connection USERNAME to the database.

**Importance:** HIGH

**Type:** STRING






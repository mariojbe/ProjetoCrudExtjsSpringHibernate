Ext.define('ExtMVC.store.Contatos', {
    extend: 'Ext.data.Store',
    model: 'ExtMVC.model.Contato',
    autoLoad: true,
    pageSize: 35,
    autoLoad: {start: 0, limit: 35},
    proxy: {
        type: 'ajax',
        api: {
            create: 'contact/create.action',
            read: 'contact/view.action',
            update: 'contact/update.action',
            destroy: 'contact/delete.action'
        },
        reader: {
            type: 'json',
            root: 'data',
            successProperty: 'success'
        },
        writer: {
            type: 'json',
            writeAllFields: true,
            encode: true,
            root: 'data'
        }
    }
});
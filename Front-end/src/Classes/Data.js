class DataClass {
  DataClass(
    props
    //       {
    //     sl_no,
    //     isopen,
    //     total_open_amount,
    //     cust_number,
    //     baseline_create_date,
    //     business_code,
    //     clear_date,
    //     posting_id,
    //     area_business,
    //     doc_id,
    //     cust_payment_terms,
    //     is_deleted,
    //     buisness_year,
    //     document_create_date1,
    //     invoice_id,
    //     document_create_date,
    //     posting_date,
    //     invoice_currency,
    //     due_in_date,
    //     document_type,
    //   }
  ) {
    this.sl_no = props.sl_no;
    this.isopen = props.isopen;
    this.total_open_amount = props.total_open_amount;
    this.cust_number = props.cust_number;
    this.baseline_create_date = props.baseline_create_date;
    this.business_code = props.business_code;
    this.clear_date = props.clear_date;
    this.posting_id = props.posting_id;
    this.area_business = props.area_business;
    this.doc_id = props.doc_id;
    this.cust_payment_terms = props.cust_payment_terms;
    this.is_deleted = props.is_deleted;
    this.buisness_year = props.buisness_year;
    this.document_create_date1 = props.document_create_date1;
    this.invoice_id = props.invoice_id;
    this.document_create_date = props.document_create_date;
    this.posting_date = props.posting_date;
    this.invoice_currency = props.invoice_currency;
    this.due_in_date = props.due_in_date;
    this.document_type = props.document_type;
  }

  toJson() {
    return Object.getOwnPropertyNames(this).reduce((a, b) => {
      a[b] = this[b];
      return a;
    }, {});
  }
}
module.exports = DataClass;

<template>
  <table id="table-id"></table>
</template>

<script>
export default {
    props: ['tableData'],
    data() {
      return {
        rows: [],
        headers: [
          { title: 'column 1' },
          { title: 'column 2' },
          { title: 'column 3', class: 'some-class'}
        ],
        dtHandle: null
      }
    },
    watch: {
      tableData(val, oldVal) {
        let vm = this;
        vm.rows = [];
        val.forEach(function (item) {
          let row = [];
          row.push(item.col_one_data);
          row.push(item.col_two_data);
          row.push(item.col_three_data);
 
          vm.rows.push(row);
        });
 
        // This is where the "magic" happens...
        vm.dtHandle.clear();
        vm.dtHandle.rows.add(vm.rows);
        vm.dtHandle.draw();
      }
    },
    ready() {
      // Determine the height of the table based on where it is on the page, 
      // or force a minimum of 300px if it's too close to the bottom of the window.
      let scrollY = (Math.floor(
        window.innerHeight - this.$el.getBoundingClientRect().top
      ) - 140);
      if (scrollY < 300) {
        scrollY = 300;
      }
      // Fire up datatables with our desired config
      // and store a reference handle to our component's
      // data element so we can reference it later..
      this.dtHandle = $(this.$el).DataTable({
        columns: this.headers,
        data: this.rows,
        data: this.rows,
        searching: false,
        paging: false,
        fixedHeader: true,
        fixedColumns: true,
        scrollY: scrollY + 'px',
        scrollX: true,
        info: false,
        buttons: [
          {
            extend: 'colvis',
            collectionLayout: 'fixed two-column',
            text: 'Show/Hide Data'
          },
          {
            extend: 'csv',
            text: 'Export CSV'
          },
          {
            extend: 'excel',
            text: 'Export Excel'
          }
        ]
      });
 
      // Prepend the buttons to the wrapper so they appear above the table:
      this.dtHandle.buttons()
          .container()
          .appendTo('#table-id_wrapper');
    }
  }
</script>

<style scoped>

</style>

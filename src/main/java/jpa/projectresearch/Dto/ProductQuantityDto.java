package jpa.projectresearch.Dto;

public  class ProductQuantityDto {
        private Long productId;

        private int quantity;

        private String productName;

        private String description;

        private Double price;

        private String imageUrl;

        private int stock_quantity;

        private int number_Of_Purchases;

        public ProductQuantityDto(Long productId, int quantity, String productName, String description, Double price, String imageUrl, int stock_quantity, int number_Of_Purchases) {
            this.productId = productId;
            this.quantity = quantity;
            this.productName = productName;
            this.description = description;
            this.price = price;
            this.imageUrl = imageUrl;
            this.stock_quantity = stock_quantity;
            this.number_Of_Purchases = number_Of_Purchases;
        }



        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getStock_quantity() {
            return stock_quantity;
        }

        public void setStock_quantity(int stock_quantity) {
            this.stock_quantity = stock_quantity;
        }

        public int getNumber_Of_Purchases() {
            return number_Of_Purchases;
        }

        public void setNumber_Of_Purchases(int number_Of_Purchases) {
            this.number_Of_Purchases = number_Of_Purchases;
        }
    }
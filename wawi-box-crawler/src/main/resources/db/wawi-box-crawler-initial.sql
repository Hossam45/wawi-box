
CREATE SEQUENCE "images_pk_seq";
CREATE SEQUENCE "product_id_seq";

CREATE TABLE "images" (

  "Product_ID" VARCHAR(255) COLLATE "default",
  "image"      VARCHAR(255) COLLATE "default",
  "id"         BIGINT NOT NULL DEFAULT nextval('images_pk_seq'::regclass),

  CONSTRAINT "Images_pkey" PRIMARY KEY ("id")
);

CREATE TABLE "products" (
  "_raw"         TEXT COLLATE "default",
  "title"        TEXT COLLATE "default",
  "manufacturer" TEXT COLLATE "default",
  "product_id"   VARCHAR(255) COLLATE "default",
  "description"  TEXT COLLATE "default",
  "id"          BIGINT NOT NULL DEFAULT nextval('product_id_seq'::regclass),

  CONSTRAINT "Products_pkey" PRIMARY KEY ("id")
);


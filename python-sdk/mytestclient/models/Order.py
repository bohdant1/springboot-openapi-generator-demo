from typing import *

from pydantic import BaseModel, Field


class Order(BaseModel):
    """
    None model

    """

    model_config = {"populate_by_name": True, "validate_assignment": True}

    id: Optional[str] = Field(validation_alias="id", default=None)

    productName: Optional[str] = Field(validation_alias="productName", default=None)

    price: Optional[float] = Field(validation_alias="price", default=None)

    customerName: Optional[str] = Field(validation_alias="customerName", default=None)
